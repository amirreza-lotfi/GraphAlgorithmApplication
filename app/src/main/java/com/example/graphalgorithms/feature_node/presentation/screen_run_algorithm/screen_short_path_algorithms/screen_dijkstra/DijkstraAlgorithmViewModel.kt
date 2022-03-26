package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_short_path_algorithms.screen_dijkstra

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.UndirectedGraphProvider
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_short_path_algorithms.screen_dijkstra.util.DijkstraUiEvent
import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch


class DijkstraAlgorithmViewModel(hiltViewModel: UndirectedGraphProvider):ViewModel() {
    val nodeList = hiltViewModel.nodeList
    val edgeList = hiltViewModel.edgeList

    val nodesLabel = hiltViewModel.getLabelsName()

    private val _uiEvent = MutableSharedFlow<DijkstraUiEvent>()
    val uiEvent:SharedFlow<DijkstraUiEvent> = _uiEvent.asSharedFlow()

    var startingNode:String = nodeList[0].label

    fun onPlayButtonClicked(){
        dijkstraAlgorithms()
    }

    var visibility = mutableStateOf(MutableList(nodeList.size) { false })
    var distance = mutableStateOf(MutableList(nodeList.size) { Float.POSITIVE_INFINITY })
    var lastNode = mutableStateOf(MutableList(nodeList.size){startingNode})


    private fun dijkstraAlgorithms(){
        viewModelScope.launch {
            resetTable()

//            setUpLastNodeTable()
//
//            val indexOfStart = nodesLabel.indexOf(startingNode)
//
//            visibility[indexOfStart] = true
//            distance[indexOfStart] = 0f
//
//            for (edge: Edge in findNodeByLabel(nodesLabel[indexOfStart], nodeList).edges) {
//                val indexOfToNode = nodesLabel.indexOf(edge.nodeTo.label)
//                if (edge.weight + distance[indexOfStart] < distance[indexOfToNode] && !visibility[indexOfToNode]) {
//                    distance[indexOfToNode] = edge.weight + distance[indexOfStart]
//                }
//            }
//
//            for (i in 1..nodeList.size - 2) {
//                val indexOfSmallestDistance = findIndexOfSmallestItemInList(visibility, distance)
//                //emit Node to represent and node-last edge
//                _uiEvent.emit(DijkstraUiEvent.EmitVisitedNode(nodeList[indexOfSmallestDistance]))
//                val edge = getEdgeOf(
//                    nodesLabel[indexOfSmallestDistance],
//                    lastNode[indexOfSmallestDistance]
//                )
//                if (edge != null)
//                    _uiEvent.emit(DijkstraUiEvent.EmitVisitedEdge(edge))
//
//                visibility[indexOfSmallestDistance] = true
//
//                delay(2000L)
//                for (edge: Edge in findNodeByLabel(
//                    nodesLabel[indexOfSmallestDistance],
//                    nodeList
//                ).edges) {
//                    val indexOfToNode = nodesLabel.indexOf(edge.nodeTo.label)
//                    if (edge.weight + distance[indexOfSmallestDistance] < distance[indexOfToNode] && !visibility[indexOfToNode]) {
//                        distance[indexOfToNode] = edge.weight + distance[indexOfSmallestDistance]
//                    }
//                }
//                _uiEvent.emit(DijkstraUiEvent.EmitDistanceArray(distance))
//                _uiEvent.emit(DijkstraUiEvent.EmitVisibilityArray(visibility))
//
//            }
//

        }

    }
    
    private fun findIndexOfSmallestItemInList(visibility:BooleanArray, distance:FloatArray):Int{
        var minimumIndex = -1
        var minimumValue = Float.POSITIVE_INFINITY

        distance.onEachIndexed(){index, value ->
            if(!visibility[index] && value<minimumValue){
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

    private fun resetTable(){
        visibility.value.clear()
        distance.value.clear()
        lastNode.value.clear()
    }

    private fun setUpLastNodeTable(){
        for (i in 1..nodesLabel.size)
            lastNode.value.add(startingNode)
    }

    private fun updateVisitedEdge(){

    }
}