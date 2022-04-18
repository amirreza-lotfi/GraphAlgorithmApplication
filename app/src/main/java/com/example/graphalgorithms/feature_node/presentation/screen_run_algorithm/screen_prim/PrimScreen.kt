package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_prim

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
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
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_bfs_traversal.component.*
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_choose_algorithms_screen.component.CustomBackButton
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_dfs_traversal.component.DataComposable
import com.example.graphalgorithms.feature_node.presentation.ui.theme.lightGray
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
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

    var isPlayButtonVisible by rememberSaveable {
        mutableStateOf(true)
    }

    BoxOfAlgorithm {
        CustomBackButton {
            navController.popBackStack()
        }

        Box(
            Modifier
                .padding(top = 48.dp)
                .fillMaxSize()) {



            DrawGraphEdges(primViewModel.edgeList)
            DrawGraphNodes(primViewModel.nodeList)

            DrawVisitedEdgeWithRedColor(visitedEdges)
            DrawVisitedNodeWithRedColor(visitedNodes)



            visitedNodeText = primViewModel.getVisitedNodeTextForScreen(visitedNodes)

            DataComposable(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .align(Alignment.BottomCenter),
                isPlayButtonVisible = isPlayButtonVisible,
                visitedNodes = visitedNodeText,
                onPlayButtonClick = {
                    visitedNodeText = StringBuffer("")
                    visitedNodes.clear()
                    visitedEdges.clear()
                    primViewModel.onPlayButtonClicked()
                    isPlayButtonVisible = false
                }
            )


        }

    }

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