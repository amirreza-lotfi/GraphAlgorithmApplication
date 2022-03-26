package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_basic_algorithms.screen_dfs_traversal.component

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_basic_algorithms.screen_bfs_traversal.component.PlayAlgorithmsButton
import com.example.graphalgorithms.feature_node.presentation.ui.theme.lightYellow

@Composable
fun DataComposable(
    modifier: Modifier,
    isPlayButtonVisible:Boolean,
    visitedNodes: StringBuffer,
    onPlayButtonClick:()->Unit
) {
    val scrollableState = rememberScrollableState {
        1f
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            Modifier.fillMaxSize()
        ) {

            Box(
                Modifier
                    .fillMaxWidth()
                    .height(84.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(lightYellow)
                    .scrollable(scrollableState, orientation = Orientation.Vertical)
                    .padding(8.dp)
            ) {
                Text(
                    text = visitedNodes.toString(), fontSize = 24.sp
                )
            }

            PlayAlgorithmsButton(
                modifier = Modifier
                    .width(160.dp)
                    .align(Alignment.BottomCenter),
                isPlayButtonVisible = isPlayButtonVisible,
                onClick = {
                    onPlayButtonClick()
                }
            )
        }

    }
}