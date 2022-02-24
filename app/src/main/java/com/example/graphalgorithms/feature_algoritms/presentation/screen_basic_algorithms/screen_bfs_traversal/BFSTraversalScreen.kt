package com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.screen_bfs_traversal

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.screen_bfs_traversal.component.*
import com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.screen_bfs_traversal.util.BfsUiEvent
import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import com.example.graphalgorithms.feature_node.domain.entitiy.Node
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun BFSTraversalScreen(
    navController: NavController,
    bfsViewModel: BFSTraversalViewModel = BFSTraversalViewModel(
        hiltViewModel(
            navController.getBackStackEntry("BasicAlgorithmsScreen")
        )
    )
) {
    val viewModelScope = rememberCoroutineScope()

    val visitedNodes = remember{ mutableStateListOf<Node>()}
    val visitedEdges = remember{ mutableStateListOf<Edge>()}

    var visitedNodeText = StringBuffer("")

    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        var isPlayButtonVisible by rememberSaveable {
            mutableStateOf(true)
        }

        DrawGraphEdges(bfsViewModel.edgeList)
        DrawGraphNodes(bfsViewModel.nodeList)

        DrawVisitedEdgeWithRedColor(visitedEdges)
        DrawVisitedNodeWithRedColor(visitedNodes)

        PlayAlgorithmsButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .width(160.dp),
            isPlayButtonVisible = isPlayButtonVisible,
            onClick = {
                visitedNodeText = StringBuffer("")
                visitedNodes.clear()
                visitedEdges.clear()
                bfsViewModel.onPlayButtonClicked()
                isPlayButtonVisible = false
            }
        )

        visitedNodes.forEachIndexed { index, node ->
            visitedNodeText.append(node.label)
            if (index-2 != bfsViewModel.nodeList.size) {
                visitedNodeText.append(" -> ")
            }
        }
        Column(
            Modifier
                .fillMaxWidth()
                .height(160.dp)
                .padding(bottom = 64.dp, start = 24.dp, end = 24.dp)
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = visitedNodeText.toString(), fontSize = 24.sp
            )
        }

        viewModelScope.launch {
            bfsViewModel.bfsUiEvent.collect { event ->
                when (event) {
                    is BfsUiEvent.DrawNodeOnScreen -> {
                        visitedNodes.add(event.node)
                    }
                    is BfsUiEvent.DrawEdgeOnScreen -> {
                        visitedEdges.add(event.edge)
                    }
                    is BfsUiEvent.BFSAlgorithmsEnds -> {
                        isPlayButtonVisible = true
                    }
                }
            }
        }
    }
}