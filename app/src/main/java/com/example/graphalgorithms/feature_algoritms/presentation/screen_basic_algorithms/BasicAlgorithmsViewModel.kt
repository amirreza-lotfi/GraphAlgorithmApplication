package com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms


import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import com.example.graphalgorithms.feature_algoritms.presentation.RunAlgorithmsViewModel
import com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.components_of_basic_algorithms.util.BasicAlgorithmsEvent
import com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.screen_bfs_traversal.util.BfsUiEvent
import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import com.example.graphalgorithms.feature_node.domain.entitiy.Node
import com.example.graphalgorithms.feature_node.presentation.NodeFeatureViewModel
import com.example.graphalgorithms.feature_node.presentation.NodeFeatureViewModel.Companion.findNodeByLabel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import java.util.*


class BasicAlgorithmsViewModel(val hiltViewModel: RunAlgorithmsViewModel) :ViewModel(){

    var starterNodeForBfsAlgorithms:String = NodeFeatureViewModel.getNodeLabels()[0]

    var nodeList:List<Node> = hiltViewModel.nodeList
    var edgeList:List<Edge> = hiltViewModel.edgeList




    suspend fun onEvent(event: BasicAlgorithmsEvent){
        when(event){
            is BasicAlgorithmsEvent.OnNavigateToBFSScreen->{
                hiltViewModel.starterNodeForAlgorithms = starterNodeForBfsAlgorithms
            }
            is BasicAlgorithmsEvent.OnNavigateToDFSScreen->{
                hiltViewModel.starterNodeForAlgorithms = starterNodeForBfsAlgorithms
            }
        }
    }

    fun isGraphComplete():Boolean{
        if(nodeList.isEmpty())
            return false
        val nodesCount = nodeList.size
        val edgesCount = edgeList.size

        return nodesCount*(nodesCount-1) == edgesCount
    }


}