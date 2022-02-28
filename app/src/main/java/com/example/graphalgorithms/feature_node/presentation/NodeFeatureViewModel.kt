package com.example.graphalgorithms.feature_node.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import com.example.graphalgorithms.feature_node.domain.entitiy.EdgeInDatabase
import com.example.graphalgorithms.feature_node.domain.entitiy.Node
import com.example.graphalgorithms.feature_node.domain.use_case.UseCases
import com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.util.AddEdgeEvent
import com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.util.AddEditNodeScreenEvent
import com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.util.UiEvent
import com.example.graphalgorithms.feature_node.presentation.screen_graph.util.AddEditScreenEntity
import com.example.graphalgorithms.feature_node.presentation.screen_graph.util.NodeInformation
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
    private val _nodeList = mutableStateListOf<Node>()
    val nodeList: SnapshotStateList<Node> = _nodeList

    private var _edgeList = mutableStateListOf<Edge>()
    var edgeList:SnapshotStateList<Edge> = _edgeList

    private val _isAnyNodeSelected = mutableStateOf(false)
    val isAnyNodeSelected:State<Boolean> = _isAnyNodeSelected

    private var getNotesJob: Job? = null

    val redrawEdges = mutableStateOf(1)

    private val _uiEventFlow = MutableSharedFlow<UiEvent>()
    val uiEventFlow = _uiEventFlow.asSharedFlow()

    private val _runAlgorithmButtonVisibility = mutableStateOf(true)
    val runAlgorithmButtonVisibility:State<Boolean> = _runAlgorithmButtonVisibility

    //Add edit screen properties
    private val _addEditScreenEntity = mutableStateOf(AddEditScreenEntity())
    val addEditScreenEntity:State<AddEditScreenEntity> = _addEditScreenEntity

    val counterForRecomposition = mutableStateOf(1)


    init{
        this.getGraphFromDataBase()
    }

    fun onAddEditScreenEvent(eventEdit: AddEditNodeScreenEvent){
        when(eventEdit){
            is AddEditNodeScreenEvent.OnSaveEditNodeButtonClicked-> {
                setAddedNodeLabel(eventEdit.label)
                saveValidation()
            }
            is AddEditNodeScreenEvent.OnCancelEditNodeButtonClicked->{
                resetAddEditEntity()
            }
            is AddEditNodeScreenEvent.SaveEdgeButtonClicked->{
                recomposeEdgeListPresentation()

                val nodeFrom = _addEditScreenEntity.value.newNode
                val nodeTo = findNodeByLabel(eventEdit.toNodeLabel)
                val weight = validatedWeight()

                val edge = Edge(
                    nodeFrom,
                    nodeTo,
                    weight
                )
                _addEditScreenEntity.value.edges.add(edge)
            }
        }
    }
    fun onEdgeEvent(event: AddEdgeEvent){
        when(event){
            is AddEdgeEvent.OnWeightChanged->{
                _addEditScreenEntity.value = _addEditScreenEntity.value.copy(
                    weightOfEdge = event.weight
                )
            }
            is AddEdgeEvent.OnDeleteEdgeClicked->{
                removeEdgeFromNewNode(event.edge)
            }
        }
    }

    fun onScreenGraphEvent(event:ScreenGraphEvent){
        when(event){
            is ScreenGraphEvent.OnNodeClicked -> {
                setSelectedNodeSetting(event.node)
                setAllNodesUnselected(event.node.label)
            }
            is ScreenGraphEvent.NodePositionChanged->{
                event.node.setPosition(event.x,event.y)
                recomposeEdgesPresentation()
            }
            is ScreenGraphEvent.OnNavigateToEditScreen ->{
                resetAddEditEntity()

                val selectedNode = findSelectedNode()
                _addEditScreenEntity.value = _addEditScreenEntity.value.copy(
                    newNode = selectedNode
                )
                for(edge:Edge in edgeList) {
                    if(edge.nodeFrom.label == selectedNode.label ||
                        edge.nodeTo.label == selectedNode.label)
                        _addEditScreenEntity.value.edges.add(edge.copy())
                }
                _addEditScreenEntity.value = _addEditScreenEntity.value.copy(
                    titleOfAddEditScreen = "Edit Node"
                )
            }
            is ScreenGraphEvent.OnNavigateToAddScreen->{
                resetAddEditEntity()

                _addEditScreenEntity.value = _addEditScreenEntity.value.copy(
                    titleOfAddEditScreen = "Add Node"
                )
            }
            is ScreenGraphEvent.OnNavigateToRunAlgorithms->{
                viewModelScope.launch {
                    saveGraphInDatabase()
                }
            }
            is ScreenGraphEvent.SetRunAlgorithmButtonVisibility->{
                _runAlgorithmButtonVisibility.value = event.visibility
            }
            is ScreenGraphEvent.DeleteSelectedNode->{
                val deletedNode = findSelectedNode()
                val mustDeletedEdgeFromDb = mutableListOf<Edge>()

                for(edge:Edge in _edgeList){
                    if(edge.nodeTo.label == deletedNode.label ||
                        edge.nodeFrom.label == deletedNode.label){
                        mustDeletedEdgeFromDb.add(edge)
                    }
                }

                viewModelScope.launch {
                    saveGraphInDatabase()
                    deleteNodeFromDataBase(deletedNode)
                    for(edge:Edge in mustDeletedEdgeFromDb){
                        deleteEdgeFromDataBase(edge)
                    }

                    getGraphFromDataBase()
                    _isAnyNodeSelected.value = false
                }




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

    private suspend fun saveGraphInDatabase(){
        putNodesInDataBase()
        putEdgesInDataBase()
    }

    private suspend fun deleteNodeFromDataBase(deletedNode:Node){
        useCases.deleteNodeUseCase(deletedNode)
    }

    private suspend fun deleteEdgeFromDataBase(edge:Edge){
        useCases.deleteEdgeUseCase(edge)
    }

    private suspend fun putNodesInDataBase(){
        for(node:Node in _nodeList)
            useCases.addNodeUseCase(node)
    }

    private suspend fun putEdgesInDataBase(){
        for(edge:Edge in _edgeList)
            useCases.addEdgeUseCase(edge)
    }

    private fun saveValidation(){
        var validationLabelResponse = isNodeLabelValid()

        if(validationLabelResponse == "valid"){
            if(isEditScreen() && hasNodeSaveBefore())
                editNewNodeAndAddToNodeList()
            else {
                if(hasNodeSaveBefore()){
                    validationLabelResponse = "This label has already been selected. Please choose another name"
                }else {
                    addNewNodeToNodeList()
                }
            }
        }
        else{
            viewModelScope.launch {
                _uiEventFlow.emit(UiEvent.ShowErrorSnackbar(validationLabelResponse))
            }
        }

    }
    private fun editNewNodeAndAddToNodeList(){
        val entity by _addEditScreenEntity

        addEdgesToEdgeListWhichAddedInAddEditScreen()

        val edgeOfNewNode = edgesOfTheNodeAreAtTheTailOrHead()

        for(edge:Edge in edgeOfNewNode){
            if(!entity.edges.contains(edge)){
                edgeList.remove(edge)
                edgeIdRepository.remove(edge.id)
            }
        }

        addNodeLabelToLabelRepository(entity.newNode.label)
        viewModelScope.launch {
            _uiEventFlow.emit(UiEvent.SaveNode)
        }

    }

    private fun addEdgesToEdgeListWhichAddedInAddEditScreen(){
        for(edge:Edge in _addEditScreenEntity.value.edges){
            if(!edgeList.contains(edge)){
                edgeList.add(edge)
                edgeIdRepository.add(edge.id)
            }
        }
    }

    private fun edgesOfTheNodeAreAtTheTailOrHead():MutableList<Edge>{
        val edgeOfNewNode = mutableListOf<Edge>()
        for(edge:Edge in edgeList){
            if(edge.nodeFrom.label == _addEditScreenEntity.value.newNode.label ||
                edge.nodeTo.label == _addEditScreenEntity.value.newNode.label)
                edgeOfNewNode.add(edge)
        }
        return edgeOfNewNode
    }

    private fun addNewNodeToNodeList(){
        addEdgesInAddEditEntityToEdgeList()
        addNewNodeInAddEditEntityToNodeList()
        viewModelScope.launch {
            _uiEventFlow.emit(UiEvent.SaveNode)
        }
    }

    private fun addEdgesInAddEditEntityToEdgeList(){
        for(edge:Edge in _addEditScreenEntity.value.edges){
            edgeList.add(edge)
            edgeIdRepository.add(edge.id)
        }
    }

    private fun addNewNodeInAddEditEntityToNodeList(){
        nodeList.add(_addEditScreenEntity.value.newNode)
        addNodeLabelToLabelRepository(_addEditScreenEntity.value.newNode.label)
    }

    private fun isNodeLabelValid():String{
        val label = _addEditScreenEntity.value.newNode.label

        if(label.isEmpty()){
            return "The label is empty. choose label for node"
        }
        return if(label.length > 1){
            "The label is too long. please type only one letter "
        } else{
            "valid"
        }
    }

    private fun hasNodeSaveBefore():Boolean{
        val label = _addEditScreenEntity.value.newNode.label

        return isLabelExist(label)
    }

    private fun isEditScreen():Boolean{
        return _addEditScreenEntity.value.titleOfAddEditScreen != "Add Node"
    }

    private fun setAddedNodeLabel(label:String){
        _addEditScreenEntity.value.newNode.label = label
    }

    private fun validatedWeight():Float{
        return try {
            _addEditScreenEntity.value.weightOfEdge.toFloat()
        }catch (e:Exception){
            0f
        }
    }

    private fun findNodeByLabel(s:String):Node{
        for(node:Node in _nodeList)
            if(node.label == s)
                return node
        return Node(" ")
    }

    private fun removeEdgeFromNewNode(edge:Edge){
        recomposeEdgeListPresentation()
        _addEditScreenEntity.value.edges.remove(edge)
    }

    private fun recomposeEdgeListPresentation(){
        counterForRecomposition.value +=1
    }

    private fun resetAddEditEntity(){
        _addEditScreenEntity.value = AddEditScreenEntity()
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
        useCases.getEdges()
            .onEach {edgeDBEntity ->
                val edge = EdgeInDatabase.getEdge(edgeDBEntity,nodeList)
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

    private fun setAllNodesUnselected(selectedNodeLabel:String){
        for(node:Node in _nodeList)
            if(node.label != selectedNodeLabel)
                node.isNodeSelected = false
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
