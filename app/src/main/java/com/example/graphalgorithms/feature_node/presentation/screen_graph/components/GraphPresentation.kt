package com.example.graphalgorithms.feature_node.presentation.screen_graph.components

import androidx.compose.runtime.Composable
import com.example.graphalgorithms.feature_node.presentation.NodeFeatureViewModel

@Composable
fun GraphPresentation(
    viewModel: NodeFeatureViewModel
){
    EdgePresentation(viewModel)
    NodesPresentation(viewModel)
}
