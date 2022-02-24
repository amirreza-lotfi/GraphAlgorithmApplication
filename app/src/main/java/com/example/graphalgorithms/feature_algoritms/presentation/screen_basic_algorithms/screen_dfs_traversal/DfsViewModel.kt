package com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.screen_dfs_traversal


import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import com.example.graphalgorithms.feature_node.domain.entitiy.Node
import com.example.graphalgorithms.feature_algoritms.presentation.RunAlgorithmsViewModel
import com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.screen_dfs_traversal.util.DfsUiEvent
import com.example.graphalgorithms.feature_node.presentation.NodeFeatureViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.*

class DfsViewModel(val hiltViewModel: RunAlgorithmsViewModel):ViewModel(){
    val nodeList:List<Node> = hiltViewModel.nodeList
    val edgeList:List<Edge> = hiltViewModel.edgeList

    private val _dfsUiEvent = MutableSharedFlow<DfsUiEvent>()
    val dfsUiEvent:SharedFlow<DfsUiEvent> = _dfsUiEvent.asSharedFlow()

    var starterNodeForDfsAlgorithms:String = hiltViewModel.starterNodeForAlgorithms

    private var getNotesJob: Job? = null

    fun onPlayButtonClicked(){
        depthFirstTraversal(starterNodeForDfsAlgorithms)
    }
    init{
        Log.i("start", ": ")
    }

    private fun depthFirstTraversal(startNodeLabel: String) {
        setAllNodeUnSelected()
        val startingNode = NodeFeatureViewModel.findNodeByLabel(startNodeLabel,nodeList)
        dfs(startingNode)
    }

    private fun dfs(start: Node) {
        viewModelScope.launch {
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
                    delay(300L)
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
            nodeList.forEach { node->
                if(!node.isNodeSelected)
                    dfs(node)
            }
            _dfsUiEvent.emit(DfsUiEvent.OnAlgorithmEnds)
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
    fun setAllNodeUnSelected(){
        for(node:Node in nodeList)
            node.isNodeSelected = false
    }

}