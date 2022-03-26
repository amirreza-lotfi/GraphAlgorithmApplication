package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_basic_algorithms.screen_dfs_traversal


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import com.example.graphalgorithms.feature_node.domain.entitiy.Node
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.UndirectedGraphProvider
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_basic_algorithms.screen_dfs_traversal.util.DfsUiEvent
import com.example.graphalgorithms.feature_node.presentation.NodeFeatureViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.*

class DfsViewModel(val hiltViewModel: UndirectedGraphProvider):ViewModel(){
    val nodeList:List<Node> = hiltViewModel.nodeList
    val edgeList:List<Edge> = hiltViewModel.edgeList

    private val _dfsUiEvent = MutableSharedFlow<DfsUiEvent>()
    val dfsUiEvent:SharedFlow<DfsUiEvent> = _dfsUiEvent.asSharedFlow()

    var starterNodeForDfsAlgorithms:String = "a"

    private var dfsRunJob: Job? = null

    fun onPlayButtonClicked(){
        depthFirstTraversal(starterNodeForDfsAlgorithms)
    }

    private fun depthFirstTraversal(startNodeLabel: String) {
        setAllNodeUnSelected()
        val startingNode = NodeFeatureViewModel.findNodeByLabel(startNodeLabel,nodeList)
        dfs(startingNode)
    }

    private fun dfs(start: Node) {
        dfsRunJob = viewModelScope.launch {
            delay(1000L)
            val stackNodeHolder = Stack<Node>()
            val stackTraversedEdges = Stack<Edge>()

            stackNodeHolder.add(start)
            while(stackNodeHolder.isNotEmpty()){
                val lastNodeInStack = stackNodeHolder.pop()

                if(stackTraversedEdges.isNotEmpty()){
                    val traversedEdge = stackTraversedEdges.pop()
                    if(!traversedEdge.nodeTo.isNodeSelected || !traversedEdge.nodeTo.isNodeSelected)
                        _dfsUiEvent.emit(DfsUiEvent.DrawEdgeOnScreen(traversedEdge))
                }
                if(!lastNodeInStack.isNodeSelected){
                    _dfsUiEvent.emit(DfsUiEvent.DrawNodeOnScreen(lastNodeInStack))
                    delay(1000L)
                    lastNodeInStack.isNodeSelected = true
                }
                for(edge:Edge in lastNodeInStack.edges){
                    if(!edge.nodeTo.isNodeSelected) {
                        stackNodeHolder.add(edge.nodeTo)
                        stackTraversedEdges.add(edge)
                    }
                }
            }
            stackTraversedEdges.clear()
            var isAnyUnselectedNodeInGraph = false
            nodeList.forEach { node->
                if(!node.isNodeSelected) {
                    isAnyUnselectedNodeInGraph = true
                    dfs(node)
                }
            }
            if (!isAnyUnselectedNodeInGraph) {
                _dfsUiEvent.emit(DfsUiEvent.OnAlgorithmEnds)
            }
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
        for(node:Node in nodeList)
            node.isNodeSelected = false
    }

    override fun onCleared() {
        dfsRunJob?.cancel()
        super.onCleared()
    }

}