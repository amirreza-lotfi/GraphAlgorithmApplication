package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_basic_algorithms.screen_bfs_traversal

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Description
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_basic_algorithms.screen_bfs_traversal.util.BfsUiEvent
import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import com.example.graphalgorithms.feature_node.domain.entitiy.Node
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_basic_algorithms.screen_bfs_traversal.component.*
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_basic_algorithms.screen_dfs_traversal.component.DataComposable
import com.example.graphalgorithms.feature_node.presentation.ui.theme.lightGray
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun BFSTraversalScreen(
    navController: NavController,
    startingNode:String,
    bfsViewModel: BFSTraversalViewModel = BFSTraversalViewModel(
        hiltViewModel(
            navController.getBackStackEntry("BasicAlgorithmsScreen")
        )
    )
) {
    bfsViewModel.starterNodeForBfsAlgorithms = startingNode

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

        DrawGraphEdges(bfsViewModel.edgeList)
        DrawGraphNodes(bfsViewModel.nodeList)

        DrawVisitedEdgeWithRedColor(visitedEdges)
        DrawVisitedNodeWithRedColor(visitedNodes)



        visitedNodeText = bfsViewModel.getVisitedNodeTextForScreen(visitedNodes)

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
                    bfsViewModel.onPlayButtonClicked()
                    isPlayButtonVisible = false
                }
        )

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