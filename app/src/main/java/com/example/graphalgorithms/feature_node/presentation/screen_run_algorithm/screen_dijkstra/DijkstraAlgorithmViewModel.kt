package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_dijkstra

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.UndirectedGraphProvider
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_dijkstra.util.DijkstraUiEvent
import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import com.example.graphalgorithms.feature_node.presentation.GraphScreenViewModel.Companion.findNodeByLabel
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_dijkstra.util.AdjacencyTable
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.lang.Exception


class DijkstraAlgorithmViewModel(hiltViewModel: UndirectedGraphProvider):ViewModel() {
    val nodeList = hiltViewModel.nodeList
    val edgeList = hiltViewModel.edgeList

    val nodesLabel = hiltViewModel.getLabelsName()

    private val _uiEvent = MutableSharedFlow<DijkstraUiEvent>()
    val uiEvent:SharedFlow<DijkstraUiEvent> = _uiEvent.asSharedFlow()

    private var startingNode:String = nodeList[0].label

    private var viewModelJob: Job? = null

    fun onPlayButtonClicked(){
        dijkstraAlgorithms()
    }

    var visibility = mutableStateOf(IntArray(nodeList.size) { 0 })
    var distance = mutableStateOf(FloatArray(nodeList.size) { Float.POSITIVE_INFINITY })
    var lastNode = mutableStateOf(MutableList(nodeList.size){startingNode})


    private fun dijkstraAlgorithms(){
        viewModelJob = viewModelScope.launch {
            resetTables()

            val indexOfStart = nodesLabel.indexOf(startingNode)

            visibility.value[indexOfStart] = 1
            distance.value[indexOfStart] = 0f

            setDistanceOfNodeToOtherNodes(indexOfStart)

            emitVisitedNode(indexOfStart)
            emitAdjacencyTable()

            delay(3000L)
            try {
                for (i in 1 until nodeList.size) {
                    val indexOfSmallestDistance =
                        findIndexOfSmallestItemInList(visibility.value, distance.value)

                    emitVisitedNode(indexOfSmallestDistance)

                    val edge = getEdgeOf(
                        nodesLabel[indexOfSmallestDistance],
                        lastNode.value[indexOfSmallestDistance]
                    )

                    emitVisitedEdge(edge)

                    setNodeVisitedAndLastNode(indexOfSmallestDistance, 1, edge!!.nodeTo.label)

                    setDistanceOfNodeToOtherNodes(indexOfSmallestDistance)

                    emitAdjacencyTable()

                    delay(4000L)
                }

                emitAlgorithmEnded()

            }catch (e:Exception){
                emitAlgorithmEnded()
            }
        }
    }

    private suspend fun emitAdjacencyTable(){
        _uiEvent.emit(DijkstraUiEvent.EmitAdjacencyTable(
                AdjacencyTable(
                    visibilityArray = visibility.value.clone(),
                    distances = distance.value.clone(),
                    lastNodeArray = lastNode.value
                )
            )
        )
    }

    private suspend fun emitVisitedNode(indexOfStart: Int){
        _uiEvent.emit(DijkstraUiEvent.EmitVisitedNode(nodeList[indexOfStart]))
    }

    private suspend fun emitVisitedEdge(edge: Edge?){
        if (edge != null)
            _uiEvent.emit(DijkstraUiEvent.EmitVisitedEdge(edge))
    }

    private suspend fun emitAlgorithmEnded(){
        _uiEvent.emit(DijkstraUiEvent.EmitAlgorithmEnd)
    }

    private fun setNodeVisitedAndLastNode(indexOfNode:Int, visibilityValue:Int, lastNodeLabel:String){
        visibility.value[indexOfNode] = visibilityValue
        lastNode.value[indexOfNode] = lastNodeLabel
    }
    fun setStartingNodeLabel(start:String){
        startingNode = start
    }

    private fun setDistanceOfNodeToOtherNodes(indexOfStart:Int){
        val edgesOfNode = findNodeByLabel(nodesLabel[indexOfStart], nodeList).edges
        for (edge: Edge in edgesOfNode) {
            val indexOfToNode = nodesLabel.indexOf(edge.nodeTo.label)
            if (hasNewDistanceShorterThanExist(indexOfStart,indexOfToNode,edge)) {
                distance.value[indexOfToNode] = edge.weight + distance.value[indexOfStart]
                lastNode.value[indexOfToNode] = nodeList[indexOfStart].label
            }
        }
    }

    private fun hasNewDistanceShorterThanExist(indexOfStart: Int,indexOfToNode:Int, edge: Edge):Boolean{
        return edge.weight + distance.value[indexOfStart] < distance.value[indexOfToNode] && visibility.value[indexOfToNode]==0
    }

    private fun findIndexOfSmallestItemInList(visibility:IntArray, distance:FloatArray):Int{
        var minimumIndex = -1
        var minimumValue = Float.POSITIVE_INFINITY

        distance.onEachIndexed(){index, value ->
            if(visibility[index]==0 && value<minimumValue){
                minimumIndex = index
                minimumValue = value
            }
        }
        return minimumIndex
    }

    private fun getEdgeOf(fromLabel:String, toLabel:String):Edge?{
        for(edge:Edge in edgeList)
            if(
                (edge.nodeTo.label == toLabel && edge.nodeFrom.label == fromLabel) ||
                (edge.nodeFrom.label == toLabel && edge.nodeTo.label == fromLabel)
            ) {
                return edge
            }
        return null
    }

    private fun resetTables(){
        visibility.value = IntArray(nodeList.size) { 0 }
        distance.value = FloatArray(nodeList.size){ Float.POSITIVE_INFINITY }
        lastNode.value.clear()

        setUpLastNodeTable()
    }

    private fun setUpLastNodeTable(){
        for (i in 1..nodesLabel.size)
            lastNode.value.add(startingNode)
    }

    override fun onCleared() {
        viewModelJob?.cancel()
        super.onCleared()
        viewModelJob?.cancel()
    }
}