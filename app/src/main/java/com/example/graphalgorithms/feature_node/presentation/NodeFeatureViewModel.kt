package com.example.graphalgorithms.feature_node.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import com.example.graphalgorithms.feature_node.domain.entitiy.EdgeWithLabels
import com.example.graphalgorithms.feature_node.domain.entitiy.Node
import com.example.graphalgorithms.feature_node.domain.use_case.UseCases
import com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.util.AddEditNodeScreenEvent
import com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.util.UiEvent
import com.example.graphalgorithms.feature_node.presentation.screen_graph.util.EntitiesOfAddEditScreen
import com.example.graphalgorithms.feature_node.presentation.screen_graph.util.ScreenGraphEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class NodeFeatureViewModel @Inject constructor(
    private val useCases: UseCases
):ViewModel(){
    private var _nodeList = mutableStateListOf<Node>()
    var nodeList: SnapshotStateList<Node> = _nodeList

    private var _edgeList = mutableStateListOf<Edge>()
    var edgeList:SnapshotStateList<Edge> = _edgeList

    private val _isAnyNodeSelected = mutableStateOf(false)
    val isAnyNodeSelected:State<Boolean> = _isAnyNodeSelected

    private var getNotesJob: Job? = null

    val redrawEdges = mutableStateOf(1)

    private val _isAddButtonSelected = mutableStateOf(false)
    val isAddButtonSelected:State<Boolean> = _isAddButtonSelected

    private val _uiEventFlow = MutableSharedFlow<UiEvent>()
    val uiEventFlow = _uiEventFlow.asSharedFlow()

    private val _runAlgorithmButtonVisibility = mutableStateOf(true)
    val runAlgorithmButtonVisibility:State<Boolean> = _runAlgorithmButtonVisibility

    //Add edit screen properties
    private val _addEditScreenEntity = mutableStateOf(EntitiesOfAddEditScreen())
    val entitiesOfAddEditScreen:State<EntitiesOfAddEditScreen> = _addEditScreenEntity

    val counterForRecomposition = mutableStateOf(1)


    init{
        this.getGraphFromDataBase()
    }
    fun onScreenGraphEvent(event:ScreenGraphEvent){
        when(event){
            is ScreenGraphEvent.OnNodeClicked -> {
                setSelectedNodeSetting(event.node)
                setOtherNodesUnselected(event.node.label)
            }
            is ScreenGraphEvent.NodePositionChanged->{
                event.node.setPosition(event.x,event.y)
                recomposeEdgesPresentation()
            }
            is ScreenGraphEvent.OnNavigateToEditScreen ->{
                resetAddEditEntity()

                val selectedNode = findSelectedNode()
                _addEditScreenEntity.value.nodeLabel = selectedNode.label

                for(edge:Edge in edgeList) {
                    if(edge.nodeFrom.label == selectedNode.label){
                        _addEditScreenEntity.value.edges.add(
                            EdgeWithLabels(
                                edge.nodeFrom.label,
                                edge.nodeTo.label,
                                edge.weight,
                                edge.id
                            )
                        )
                    }
                    else if(edge.nodeTo.label == selectedNode.label) {
                        _addEditScreenEntity.value.edges.add(
                            EdgeWithLabels(
                                edge.nodeTo.label,
                                edge.nodeFrom.label,
                                edge.weight,
                                edge.id
                            )
                        )
                    }
                }
                _addEditScreenEntity.value.titleOfAddEditScreen = "Edit Node"
            }
            is ScreenGraphEvent.OnNavigateToAddScreen->{
                resetAddEditEntity()
                _addEditScreenEntity.value.titleOfAddEditScreen = "Add Node"
            }
            is ScreenGraphEvent.SetRunAlgorithmButtonVisibility->{
                _runAlgorithmButtonVisibility.value = event.visibility
            }
            is ScreenGraphEvent.DeleteSelectedNode->{
                val deletedNode = findSelectedNode()

                _edgeList.removeAll { edge->
                    edge.nodeFrom.label == deletedNode.label || edge.nodeTo.label == deletedNode.label
                }

                _nodeList.removeIf {
                    deletedNode.label == it.label
                }

                removeNodeFromNodeLabelsRepository(deletedNode)

                setAllNodesUnselected()
            }
        }
    }

    fun onAddEditScreenEvent(eventEdit: AddEditNodeScreenEvent){
        when(eventEdit){
            is AddEditNodeScreenEvent.OnSaveNodeButtonClicked-> {
                if(isNodeLabelValid()){
                    saveNode()
                    setAllNodesUnselected()
                }
                else{
                    showErrorSnackBar()
                }
            }
            is AddEditNodeScreenEvent.OnCancelEditNodeButtonClicked->{
                resetAddEditEntity()
            }
            is AddEditNodeScreenEvent.SaveEdgeButtonClicked->{
                recomposeEdgeListPresentation()

                val edgeWithLabels = EdgeWithLabels(
                    _addEditScreenEntity.value.nodeLabel,
                    eventEdit.toNodeLabel,
                    validatedWeight(),
                )
                _addEditScreenEntity.value.edges.add(edgeWithLabels)
            }
            is AddEditNodeScreenEvent.OnWeightChanged->{
                _addEditScreenEntity.value = _addEditScreenEntity.value.copy(
                    weightOfEdge = eventEdit.weight
                )
            }
            is AddEditNodeScreenEvent.OnNodeLabelChanged->{
                _addEditScreenEntity.value = _addEditScreenEntity.value.copy(
                    nodeLabel = eventEdit.nodeLabel
                )
            }
            is AddEditNodeScreenEvent.OnDeleteEdgeClicked->{
                removeEdgeFromNewNode(eventEdit.edge)
                _isAddButtonSelected.value = false
            }
            is AddEditNodeScreenEvent.OnAddEdgeRowSelection->{
                _isAddButtonSelected.value = !_isAddButtonSelected.value
            }
        }
    }

    private fun saveNode(){
        viewModelScope.launch {
            if(_addEditScreenEntity.value.titleOfAddEditScreen == "Add Node"){
                addNewNodeToGraph()
            }else{
                editNodeInGraph()
            }
            _uiEventFlow.emit(UiEvent.SaveNode)
        }

    }
    private fun showErrorSnackBar(){
        viewModelScope.launch {
            val errorMessageOfValidationOfNodeLabel = problemOfLabel()
            _uiEventFlow.emit(UiEvent.ShowErrorSnackbar(errorMessageOfValidationOfNodeLabel))
        }
    }
    private fun addNewNodeToGraph(){
        val newNode = Node(_addEditScreenEntity.value.nodeLabel)
        addNodeToNodeList(newNode)
        addListOfEdgeWithLabelToNodeEdges(newNode,_addEditScreenEntity.value.edges)
    }

    private fun addListOfEdgeWithLabelToNodeEdges(newNode:Node, edgeWithLabels:List<EdgeWithLabels>){
        for(edge:EdgeWithLabels in edgeWithLabels){
            val newEdge = Edge(
                newNode,
                findNodeByLabel(edge.toLabel,_nodeList),
                edge.weight,
                edge.edgeId
            )
            addEdgeToEdgeList(newEdge)
        }
    }

    private fun editNodeInGraph(){
        var index = 0
        val newNode = Node.createCopyNode(findSelectedNode())
        while (index <_edgeList.size){
            if(_edgeList[index].nodeFrom.label == newNode.label || _edgeList[index].nodeTo.label == newNode.label) {
                deleteEdgeFromEdgeList(_edgeList[index])
            }
            else{
                index++
            }
        }
        for(node:Node in _nodeList){
            if(node.label == newNode.label){
                _nodeList.remove(node)
                removeNodeFromNodeLabelsRepository(node)
                break
            }
        }
        addListOfEdgeWithLabelToNodeEdges(newNode,_addEditScreenEntity.value.edges)
        addNodeToNodeList(newNode)
    }

    private fun addNodeToNodeList(node:Node){
        _nodeList.add(node)
        addNodeLabelToLabelRepository(node.label)
    }

    private fun addEdgeToEdgeList(newEdge:Edge){
        _edgeList.add(newEdge)
        addEdgeIdToStatic(newEdge)
    }

    private fun deleteEdgeFromEdgeList(edge:Edge){
        _edgeList.remove(edge)
        edgeIdRepository.remove(edge.id)
    }



    private fun removeEdgeWithId(id:Int, edgeList:MutableList<Edge>){
        for(edge:Edge in edgeList)
        {
            if(edge.id == id) {
                edgeList.remove(edge)
                deleteEdgeFromStaticRepository(edge)
                return
            }
        }
    }
    private fun findSelectedNode():Node{
        for(node:Node in _nodeList){
            if(node.isNodeSelected)
                return node
        }
        return Node("")
    }

    suspend fun saveGraphInDatabase(){
        clearDatabase()
        putNodesInDataBase()
        putEdgesInDataBase()
    }

    private fun clearDatabase(){
        useCases.deleteAllEdges
        useCases.deleteAllNodes
    }
    private suspend fun putNodesInDataBase(){
        for(node:Node in _nodeList)
            useCases.addNodeUseCase(node)
    }
    private suspend fun putEdgesInDataBase(){
        for(edge:Edge in _edgeList)
            useCases.addEdgeUseCase(edge)
    }


    private fun isNodeLabelValid():Boolean{
        val label = _addEditScreenEntity.value.nodeLabel
        return label.isNotEmpty() && label.length == 1 && label.isNotBlank()
    }

    private fun problemOfLabel():String{
        val label = _addEditScreenEntity.value.nodeLabel
        if(label.isEmpty() || label.isBlank()){
            return "The label is empty. choose label for node"
        }
        return if(label.length > 1){
            "The label is too long. please type only one letter "
        }
        else{
            return "The name is not suitable"
        }
    }

    private fun validatedWeight():Float{
        return try {
            _addEditScreenEntity.value.weightOfEdge.toFloat()
        }catch (e:Exception){
            0f
        }
    }
    private fun removeEdgeFromNewNode(edge:EdgeWithLabels){
        recomposeEdgeListPresentation()
        _addEditScreenEntity.value.edges.remove(edge)
    }

    private fun recomposeEdgeListPresentation(){
        counterForRecomposition.value +=1
    }

    private fun resetAddEditEntity(){
        _addEditScreenEntity.value = EntitiesOfAddEditScreen()
    }

    private fun setSelectedNodeSetting(selectedNode: Node){
        if(selectedNode.isNodeSelected){
            selectedNode.isNodeSelected = false
            _isAnyNodeSelected.value = true
            _isAnyNodeSelected.value = false
        }else{
            selectedNode.isNodeSelected = true
            _isAnyNodeSelected.value = false
            _isAnyNodeSelected.value = true
        }
    }

    private fun getGraphFromDataBase(){
        _nodeList.clear()
        _edgeList.clear()

        resetStaticRepository()

        getNotesJob?.cancel()

        //fill nodeList
        getNotesJob = viewModelScope.launch {
            getNodesFromDataBase()
            getEdgesFromDataBase()
        }

    }

    private suspend fun getEdgesFromDataBase(){
        useCases.getEdges()
            .onEach {edgeDBEntity ->
                val edge = EdgeWithLabels.getEdge(edgeDBEntity,nodeList)
                _edgeList.add(edge)
                addEdgeIdToStatic(edge)
            }
    }

    private suspend fun getNodesFromDataBase(){
        useCases.getNodesUseCase()
            .onEach {nodeInDb->
                recomposeEdgesPresentation()
                _nodeList.add(nodeInDb)
                addNodeLabelToLabelRepository(nodeInDb.label)
            }
    }

    private fun resetStaticRepository(){
        nodeLabels = mutableListOf()
        edgeIdRepository = mutableListOf()
    }

    private fun recomposeEdgesPresentation(){
        redrawEdges.value +=1
    }

    private fun addEdgeIdToStatic(e:Edge){
        edgeIdRepository.add(e.id)
    }

    private fun setOtherNodesUnselected(selectedNodeLabel:String){
        for(node:Node in _nodeList)
            if(node.label != selectedNodeLabel)
                node.isNodeSelected = false
    }
    private fun setAllNodesUnselected(){
        for(node:Node in _nodeList){
            node.isNodeSelected = false
        }
        _isAnyNodeSelected.value = false
        _isAddButtonSelected.value = false
    }


    companion object{
        private var nodeLabels = mutableListOf<String>()

        private var edgeIdRepository = mutableListOf<Int>()

        fun randomEdgeId():Int{
            while(true) {
                val randomInt = Random.nextInt()
                if (!edgeIdRepository.contains(randomInt))
                    edgeIdRepository.add(randomInt)
                return randomInt
            }
        }

        fun isLabelExist(s:String):Boolean{
            return nodeLabels.contains(s)
        }

        fun findNodeByLabel(s:String, nodeList:List<Node>):Node {
            for (node: Node in nodeList)
                if (node.label == s)
                    return node
            return Node(" ")
        }

        fun addNodeLabelToLabelRepository(label:String){
            if(!nodeLabels.contains(label)){
                nodeLabels.add(label)
            }
        }

        fun hasNoNodeInGraph():Boolean{
            return nodeLabels.isEmpty()
        }

        fun getNodeLabels():MutableList<String>{
            val list = mutableListOf<String>()
            list.addAll(nodeLabels)
            return list
        }

        private fun removeNodeFromNodeLabelsRepository(node:Node){
            nodeLabels.remove(
                node.label
            )
        }
        private fun deleteEdgeFromStaticRepository(edge:Edge){
            edgeIdRepository.remove(edge.id)
        }

    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.launch {
            saveGraphInDatabase()
        }
    }
}
