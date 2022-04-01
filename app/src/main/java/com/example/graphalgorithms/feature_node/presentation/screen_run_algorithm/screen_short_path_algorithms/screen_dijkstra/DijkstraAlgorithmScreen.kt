package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_short_path_algorithms.screen_dijkstra

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.graphalgorithms.MainActivity.Companion.CHOOSE_ALGORITHMS_SCREEN_ROUT
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_short_path_algorithms.screen_dijkstra.component.AdjacencyTableComposable
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_short_path_algorithms.screen_dijkstra.util.DijkstraUiEvent
import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import com.example.graphalgorithms.feature_node.domain.entitiy.Node
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_basic_algorithms.screen_bfs_traversal.component.*
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_short_path_algorithms.screen_dijkstra.util.AdjacencyTable
import com.example.graphalgorithms.feature_node.presentation.ui.theme.lightGray
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
    viewModel.setStartingNodeLabel(startingNode)

    val viewModelScope = rememberCoroutineScope()

    val visitedNodes = remember{ mutableStateListOf<Node>() }
    val visitedEdges = remember{ mutableStateListOf<Edge>() }

    val adjacencyTable = remember{
        mutableStateOf(AdjacencyTable(
            viewModel.visibility.value,
            viewModel.distance.value,
            viewModel.lastNode.value
        ))
    }

    var isPlayButtonVisible by rememberSaveable {mutableStateOf(true)}

    Box(
        Modifier
            .fillMaxSize()
            .background(lightGray)
            .padding(16.dp)
    ){
        DrawGraphEdges(viewModel.edgeList)
        DrawGraphNodes(viewModel.nodeList)

        DrawVisitedEdgeWithRedColor(visitedEdges)
        DrawVisitedNodeWithRedColor(visitedNodes)

        TitleOfAlgorithmScreen(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .align(Alignment.TopStart),
            onBackClicked = { navController.popBackStack() },
            onDescriptionClicked = {/*Todo*/}
        )

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

        AdjacencyTableComposable(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 24.dp, vertical = 48.dp),
            labels = viewModel.nodesLabel,
            adjacencyTable = adjacencyTable.value
        )

        viewModelScope.launch {
            viewModel.uiEvent.collect { event->
                when(event){
                    is DijkstraUiEvent.EmitAdjacencyTable -> {
                       adjacencyTable.value = event.adjacencyTable
                    }
                    is DijkstraUiEvent.EmitVisitedEdge -> {
                        visitedEdges.add(event.edge)
                    }
                    is DijkstraUiEvent.EmitVisitedNode -> {
                        visitedNodes.add(event.node)
                    }
                    is DijkstraUiEvent.EmitAlgorithmEnd -> {
                        isPlayButtonVisible = true
                    }
                }
            }
        }
    }

}