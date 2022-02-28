package com.example.graphalgorithms.feature_algoritms.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import com.example.graphalgorithms.feature_node.domain.entitiy.EdgeInDatabase
import com.example.graphalgorithms.feature_node.domain.entitiy.Node
import com.example.graphalgorithms.feature_node.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UndirectedGraphProvider @Inject constructor(
    private val useCases: UseCases
): ViewModel() {
    var starterNodeForAlgorithms = ""

    var nodeList:List<Node> = listOf()
    var edgeList:List<Edge> = listOf()

    private var getNotesJob: Job? = null



    init{
        getGraphDataFromDatabase()
    }

    private fun getGraphDataFromDatabase(){
        getNotesJob?.cancel()
        viewModelScope.launch {
            getNodesFromDataBase()
            getEdgesFromDataBase()
            putNodesEdge()
        }
    }

    private suspend fun getNodesFromDataBase(){
        val mutableListNode = mutableListOf<Node>()
        useCases.getNodesUseCase()
            .onEach {
                    nodeInDb->
                mutableListNode.add(nodeInDb)
            }
        nodeList = mutableListNode
    }
    private suspend fun getEdgesFromDataBase(){
        val mutableListEdge = mutableListOf<Edge>()

        useCases.getEdges()
            .onEach { edgeDBEntity ->
                val edge = EdgeInDatabase.getEdge(edgeDBEntity,nodeList)
                mutableListEdge.add(edge)
            }
        edgeList = mutableListEdge
    }
    private fun putNodesEdge(){
        for(edge: Edge in edgeList){
            val toNode = edge.nodeTo
            val fromNode = edge.nodeFrom
            fromNode.edges.add(edge)

            val anotherEdge = Edge(
                toNode,
                fromNode,
                edge.weight,
                edge.id
            )
            toNode.edges.add(anotherEdge)
        }
    }
}