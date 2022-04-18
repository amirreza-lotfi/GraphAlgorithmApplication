package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_dfs_traversal.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_bfs_traversal.component.PlayAlgorithmsButton
import com.example.graphalgorithms.feature_node.presentation.ui.theme.teal

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
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(84.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colors.background)
                .border(1.dp, MaterialTheme.colors.primary, RoundedCornerShape(8.dp))
                .scrollable(scrollableState, orientation = Orientation.Vertical)
                .padding(16.dp)
        ) {
            Text(
                text = visitedNodes.toString(), fontSize = 24.sp,

            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        PlayAlgorithmsButton(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp)
                .width(176.dp)
                .height(48.dp)
                .clip(RoundedCornerShape(8.dp)),
            isPlayButtonVisible = isPlayButtonVisible,
            onClick = {
                onPlayButtonClick()
            }
        )
    }
}