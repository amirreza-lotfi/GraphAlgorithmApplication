package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_short_path_algorithms.screen_dijkstra

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.graphalgorithms.MainActivity.Companion.CHOOSE_ALGORITHMS_SCREEN_ROUT
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_short_path_algorithms.screen_dijkstra.component.AdjacencyTable
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_short_path_algorithms.screen_dijkstra.util.DijkstraUiEvent
import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import com.example.graphalgorithms.feature_node.domain.entitiy.Node
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_basic_algorithms.screen_bfs_traversal.component.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@Composable
fun DijkstraAlgorithmScreen (
    navController: NavController,
    startingNode:String,
    viewModel: DijkstraAlgorithmViewModel = DijkstraAlgorithmViewModel(
        hiltViewModel(
            navController.getBackStackEntry(CHOOSE_ALGORITHMS_SCREEN_ROUT)
        )
    )
){
    viewModel.startingNode = startingNode

    val viewModelScope = rememberCoroutineScope()

    val visitedNodes = remember{ mutableStateListOf<Node>() }
    val visitedEdges = remember{ mutableStateListOf<Edge>() }

    val visibilityArray = viewModel.visibility
    val distances = viewModel.distance
    val lastNode = viewModel.lastNode

    var isPlayButtonVisible by rememberSaveable {mutableStateOf(true)}

    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        DrawGraphEdges(viewModel.edgeList)
        DrawGraphNodes(viewModel.nodeList)

        DrawVisitedEdgeWithRedColor(visitedEdges)
        DrawVisitedNodeWithRedColor(visitedNodes)

        PlayAlgorithmsButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .width(160.dp),
            isPlayButtonVisible = isPlayButtonVisible,
            onClick = {
                visitedNodes.clear()
                visitedEdges.clear()
                viewModel.onPlayButtonClicked()
                isPlayButtonVisible = false
            }
        )
        AdjacencyTable(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter)
                .padding(50.dp),
            labels = viewModel.nodesLabel,
            visibility = visibilityArray.value.toList(),
            distances = distances.value.toList(),
            lastNode = lastNode.value.toList()
        )

        viewModelScope.launch {
            viewModel.uiEvent.collect { event->
                when(event){
                    is DijkstraUiEvent.EmitVisibilityArray -> {
                       // visibilityArray.value = event.visibilityArray
                    }
                    is DijkstraUiEvent.EmitDistanceArray -> {
                        //distances.value = event.distanceArray
                    }
                    is DijkstraUiEvent.EmitLastNode -> {
                       // lastNode.value = event.lastNodeArray
                    }
                    is DijkstraUiEvent.EmitVisitedEdge -> {
                        visitedEdges.add(event.edge)
                    }
                    is DijkstraUiEvent.EmitVisitedNode -> {
                        visitedNodes.add(event.node)
                    }
                }
            }
        }
    }

}