package com.example.graphalgorithms.feature_node.presentation.screen_graph.components

import androidx.compose.runtime.Composable
import com.example.graphalgorithms.feature_node.presentation.ScreenGraphViewModel

@Composable
fun GraphPresentation(
    viewModel: ScreenGraphViewModel
){
    EdgePresentation(viewModel)
    NodesPresentation(viewModel)
}
