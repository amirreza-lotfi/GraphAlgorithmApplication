package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_dijkstra

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.graphalgorithms.MainActivity.Companion.CHOOSE_ALGORITHMS_SCREEN_ROUT
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_dijkstra.component.AdjacencyTableComposable
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_dijkstra.util.DijkstraUiEvent
import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import com.example.graphalgorithms.feature_node.domain.entitiy.Node
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_bfs_traversal.component.*
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_choose_algorithms_screen.component.CustomBackButton
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_dijkstra.util.AdjacencyTable
import com.example.graphalgorithms.feature_node.presentation.ui.theme.lightGray
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
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

    BoxOfAlgorithm{
        CustomBackButton {
            navController.popBackStack()
        }

        Box(
            Modifier
                .padding(top = 48.dp)
                .fillMaxSize()) {

            DrawGraphEdges(viewModel.edgeList)
            DrawGraphNodes(viewModel.nodeList)

            DrawVisitedEdgeWithRedColor(visitedEdges)
            DrawVisitedNodeWithRedColor(visitedNodes)



            PlayAlgorithmsButton(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp)
                    .width(176.dp)
                    .height(48.dp)
                    .clip(RoundedCornerShape(8.dp)),
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
                    .padding(horizontal = 24.dp, vertical = 80.dp),
                labels = viewModel.nodesLabel,
                adjacencyTable = adjacencyTable.value
            )


        }
    }
    viewModelScope.launch {
        viewModel.uiEvent.collect { event ->
            when (event) {
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