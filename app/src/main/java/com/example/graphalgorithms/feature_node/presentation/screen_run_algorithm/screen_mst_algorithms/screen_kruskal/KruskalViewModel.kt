package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_mst_algorithms.screen_kruskal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import com.example.graphalgorithms.feature_node.domain.entitiy.Node
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.UndirectedGraphProvider
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_basic_algorithms.screen_bfs_traversal.util.BfsUiEvent
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_mst_algorithms.screen_kruskal.util.KruskalUiEvent
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class KruskalViewModel(hiltViewModel:UndirectedGraphProvider):ViewModel() {
    val nodeList = hiltViewModel.nodeList
    val edgeList = hiltViewModel.edgeList.sortedBy { edge ->
        edge.weight
    }

    private var kruskalRunJob : Job? = null

    private val _kruskalUiEvent = MutableSharedFlow<KruskalUiEvent>()
    val kruskalUiEvent: SharedFlow<KruskalUiEvent> = _kruskalUiEvent.asSharedFlow()

    fun onPlayButtonClicked(){
        kruskalAlgorithm()
    }

    private fun kruskalAlgorithm(){
        kruskalRunJob = viewModelScope.launch {
            delay(1000L)
            setAllNodeUnSelected()

            for(edge: Edge in edgeList){
                val nodeFrom = edge.nodeFrom
                val nodeTo = edge.nodeTo

                if(!(nodeTo.isNodeSelected && nodeFrom.isNodeSelected)) {
                    _kruskalUiEvent.emit(KruskalUiEvent.EmitVisitedEdge(edge))

                    if(!nodeTo.isNodeSelected){
                        nodeTo.isNodeSelected = true
                        _kruskalUiEvent.emit(KruskalUiEvent.EmitVisitedNode(nodeTo))
                    }

                    if(!nodeFrom.isNodeSelected){
                        nodeFrom.isNodeSelected = true
                        _kruskalUiEvent.emit(KruskalUiEvent.EmitVisitedNode(nodeFrom))
                    }
                    delay(2500L)
                }
            }
            _kruskalUiEvent.emit(KruskalUiEvent.OnAlgorithmEnd)
        }

    }

    fun getVisitedNodeTextForScreen(visitedNodes:MutableList<Node>): StringBuffer {
        val visitedNodeText = StringBuffer(" ")
        visitedNodes.forEachIndexed { index, node ->
            visitedNodeText.append(node.label)
            if (index-2 != nodeList.size) {
                visitedNodeText.append(" -> ")
            }
        }
        return visitedNodeText
    }

    private fun setAllNodeUnSelected(){
        for(node: Node in nodeList)
            node.isNodeSelected = false
    }

    override fun onCleared() {
        kruskalRunJob?.cancel()
        super.onCleared()
    }
}