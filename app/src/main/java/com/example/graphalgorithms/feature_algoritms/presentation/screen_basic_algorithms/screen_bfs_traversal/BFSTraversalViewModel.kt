package com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.screen_bfs_traversal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graphalgorithms.feature_algoritms.presentation.RunAlgorithmsViewModel
import com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.BasicAlgorithmsViewModel
import com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.screen_bfs_traversal.util.BfsUiEvent
import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import com.example.graphalgorithms.feature_node.domain.entitiy.Node
import com.example.graphalgorithms.feature_node.presentation.NodeFeatureViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.*

class BFSTraversalViewModel(hiltViewModel: RunAlgorithmsViewModel) :ViewModel() {

    var nodeList:List<Node> = hiltViewModel.nodeList
    var edgeList:List<Edge> = hiltViewModel.edgeList

    private val _bfsUiEvent = MutableSharedFlow<BfsUiEvent>()
    val bfsUiEvent: SharedFlow<BfsUiEvent> = _bfsUiEvent.asSharedFlow()


    var starterNodeForBfsAlgorithms:String = hiltViewModel.starterNodeForAlgorithms

    fun onPlayButtonClicked(){
       breathFirstTraversal(starterNodeForBfsAlgorithms)
    }

    private fun breathFirstTraversal(startLabel:String){
        viewModelScope.launch {
            delay(2000)
            val starterNode = NodeFeatureViewModel.findNodeByLabel(startLabel, nodeList)
            val queue: Queue<Node> = ArrayDeque()
            val edgeQueue:Queue<Edge> = ArrayDeque()

            starterNode.isNodeSelected = true
            queue.add(starterNode)
            while(!queue.isEmpty()){
                if(!edgeQueue.isEmpty()){
                    val edge = edgeQueue.poll()
                    _bfsUiEvent.emit(BfsUiEvent.DrawEdgeOnScreen(edge))
                }
                val currentNode = queue.poll()
                _bfsUiEvent.emit(BfsUiEvent.DrawNodeOnScreen(currentNode))

                for(edge:Edge in currentNode.edges){
                    if(!edge.nodeTo.isNodeSelected){
                        queue.add(edge.nodeTo)
                        edge.nodeTo.isNodeSelected = true
                        edgeQueue.add(edge)
                    }
                }
                delay(1500)
            }
            _bfsUiEvent.emit(BfsUiEvent.BFSAlgorithmsEnds)
            for(node:Node in nodeList)
                node.isNodeSelected = false
        }
    }
}