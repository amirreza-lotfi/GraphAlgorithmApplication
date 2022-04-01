package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_mst_algorithms.screen_prim

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.graphalgorithms.MainActivity
import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import com.example.graphalgorithms.feature_node.domain.entitiy.Node
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_basic_algorithms.screen_bfs_traversal.component.*
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_basic_algorithms.screen_dfs_traversal.component.DataComposable
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_mst_algorithms.screen_kruskal.util.KruskalUiEvent
import com.example.graphalgorithms.feature_node.presentation.ui.theme.lightGray
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Composable
fun PrimScreen(
    navController: NavController,
    primViewModel: PrimViewModel = PrimViewModel(
        hiltViewModel = hiltViewModel(
            navController.getBackStackEntry(MainActivity.CHOOSE_ALGORITHMS_SCREEN_ROUT)
        )
    )
) {

    val viewModelScope = rememberCoroutineScope()

    val visitedNodes = remember { mutableStateListOf<Node>() }
    val visitedEdges = remember { mutableStateListOf<Edge>() }

    var visitedNodeText:StringBuffer

    Box(
        Modifier
            .fillMaxSize()
            .background(lightGray)
            .padding(16.dp)
    ) {
        TitleOfAlgorithmScreen(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .align(Alignment.TopStart),
            onBackClicked = { navController.popBackStack() },
            onDescriptionClicked = {/*Todo*/}
        )

        var isPlayButtonVisible by rememberSaveable {
            mutableStateOf(true)
        }

        DrawGraphEdges(primViewModel.edgeList)
        DrawGraphNodes(primViewModel.nodeList)

        DrawVisitedEdgeWithRedColor(visitedEdges)
        DrawVisitedNodeWithRedColor(visitedNodes)



        visitedNodeText = primViewModel.getVisitedNodeTextForScreen(visitedNodes)

        DataComposable(
            modifier = Modifier
                .fillMaxWidth()
                .height(136.dp)
                .align(Alignment.BottomCenter),
            isPlayButtonVisible = isPlayButtonVisible ,
            visitedNodes = visitedNodeText,
            onPlayButtonClick = {
                visitedNodeText = StringBuffer("")
                visitedNodes.clear()
                visitedEdges.clear()
                primViewModel.onPlayButtonClicked()
                isPlayButtonVisible = false
            }
        )

        viewModelScope.launch {
            primViewModel.primUiEvent.collect { event ->
                when (event) {
                    is PrimUiEvent.EmitVisitedNode -> {
                        visitedNodes.add(event.node)
                    }
                    is PrimUiEvent.EmitVisitedEdge -> {
                        visitedEdges.add(event.edge)
                    }
                    is PrimUiEvent.OnAlgorithmEnded -> {
                        isPlayButtonVisible = true
                    }
                }
            }
        }
    }

}