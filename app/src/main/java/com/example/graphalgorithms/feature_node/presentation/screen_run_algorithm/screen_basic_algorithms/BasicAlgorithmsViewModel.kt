package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_basic_algorithms


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.UndirectedGraphProvider
import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import com.example.graphalgorithms.feature_node.domain.entitiy.Node
import com.example.graphalgorithms.feature_node.presentation.NodeFeatureViewModel
import kotlinx.coroutines.launch
import java.util.*


class BasicAlgorithmsViewModel(val hiltViewModel: UndirectedGraphProvider) :ViewModel(){

    var starterNodeForBfsAlgorithms:String = NodeFeatureViewModel.getNodeLabels()[0]

    var nodeList:List<Node> = hiltViewModel.nodeList
    var edgeList:List<Edge> = hiltViewModel.edgeList

    private var _isGraphConnected = mutableStateOf("")
    val isGraphConnected: State<String> = _isGraphConnected

    private var _isGraphComplete = mutableStateOf("")
    val isGraphComplete: State<String> = _isGraphComplete

    private var _hasGraphCycle = mutableStateOf("false")
    val hasGraphCycle: State<String> = _hasGraphCycle

    private val _isGraphTree = mutableStateOf("true")
    val isGraphTree: State<String> = _isGraphTree

    init{
        _isGraphConnected.value = isGraphConnected().toString()
        _isGraphComplete.value = isGraphComplete().toString()
        hasGraphCycle()
    }

    private fun isGraphConnected():Boolean{
        var thereIsUnselectedNodeInGraph = false

        viewModelScope.launch {
            val starterNode = nodeList[0]
            val queue: Queue<Node> = ArrayDeque()

            starterNode.isNodeSelected = true
            queue.add(starterNode)
            while(!queue.isEmpty()){
                val currentNode = queue.poll()

                for(edge:Edge in currentNode?.edges!!){
                    if(!edge.nodeTo.isNodeSelected){
                        queue.add(edge.nodeTo)
                        edge.nodeTo.isNodeSelected = true
                    }
                }
            }
            for(node:Node in nodeList) {
                if(!node.isNodeSelected)
                    thereIsUnselectedNodeInGraph = true
            }
            setAllNodesUnselected()
        }
        return !thereIsUnselectedNodeInGraph
    }

    private fun isGraphComplete():Boolean{
        val countOfNodes = nodeList.size
        val countOfEdges = edgeList.size
        return (countOfNodes*(countOfNodes-1))/2 == countOfEdges
    }

    private fun hasGraphCycle(startingNode:Node = nodeList[0]){
        viewModelScope.launch {
            val stackNodeHolder = Stack<Node>()

            stackNodeHolder.add(startingNode)

            while(stackNodeHolder.isNotEmpty()){
                val lastNodeInStack = stackNodeHolder.pop()
                lastNodeInStack.isNodeSelected = true

                for(edge:Edge in lastNodeInStack.edges){
                    if(!edge.nodeTo.isNodeSelected) {
                        if(stackNodeHolder.contains(edge.nodeTo)){
                            _hasGraphCycle.value = true.toString()
                            _isGraphTree.value = false.toString()
                            return@launch
                        }else{
                            stackNodeHolder.add(edge.nodeTo)
                        }
                    }
                }
            }

            nodeList.forEach { node->
                if(!node.isNodeSelected) {
                    _isGraphTree.value = false.toString()
                    hasGraphCycle(node)
                }
            }
            setAllNodesUnselected()
        }
    }

    private fun isGraphTree(){

    }
    private fun setAllNodesUnselected(){
        for(node:Node in nodeList) {
            node.isNodeSelected = false
        }
    }


}