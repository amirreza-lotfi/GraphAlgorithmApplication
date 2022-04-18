package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_prim

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import com.example.graphalgorithms.feature_node.domain.entitiy.Node
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.UndirectedGraphProvider
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch


class PrimViewModel(hiltViewModel: UndirectedGraphProvider): ViewModel() {
    val nodeList = hiltViewModel.nodeList
    val edgeList = hiltViewModel.edgeList

    private var primRunJob : Job? = null

    private val _primUiEvent = MutableSharedFlow<PrimUiEvent>()
    val primUiEvent: SharedFlow<PrimUiEvent> = _primUiEvent.asSharedFlow()

    val startingNode = nodeList[0]

    fun onPlayButtonClicked(){
        primAlgorithm()
    }


    private fun primAlgorithm(){
        viewModelScope.launch {
            delay(900L)
            setAllNodeUnSelected()

            val visitedNode = mutableListOf<Node>()

            visitedNode.add(startingNode)
            startingNode.isNodeSelected = true
            _primUiEvent.emit(PrimUiEvent.EmitVisitedNode(startingNode))

            while(visitedNode.size!=nodeList.size) {
                val minimumEdge = findMinimumEdge(visitedNode)
                _primUiEvent.emit(PrimUiEvent.EmitVisitedEdge(minimumEdge))
                _primUiEvent.emit(PrimUiEvent.EmitVisitedNode(minimumEdge.nodeTo))
                visitedNode.add(minimumEdge.nodeTo)
                minimumEdge.nodeTo.isNodeSelected = true
                delay(1000L)
            }

            _primUiEvent.emit(PrimUiEvent.OnAlgorithmEnded)
        }
    }

    fun getVisitedNodeTextForScreen(visitedNodes:MutableList<Node>): StringBuffer {
        val visitedNodeText = StringBuffer(" ")
        visitedNodes.forEachIndexed { index, node ->
            visitedNodeText.append(node.label)
            if (index-2 != nodeList.size) {
                visitedNodeText.append("  ")
            }
        }
        return visitedNodeText
    }

    private fun findMinimumEdge(visitedNodes: MutableList<Node>):Edge{

        var minimumEdge = Edge(Node(""), Node(""), weight = Float.MAX_VALUE)

        for (node: Node in visitedNodes) {
            for (edge: Edge in node.edges) {
                if (!edge.nodeTo.isNodeSelected && edge.weight < minimumEdge.weight) {
                    minimumEdge = edge
                }
            }
        }
        return minimumEdge
    }
    private fun setAllNodeUnSelected(){
        for(node: Node in nodeList)
            node.isNodeSelected = false
    }

    override fun onCleared() {
        primRunJob?.cancel()
        super.onCleared()
        primRunJob?.cancel()
    }
}
