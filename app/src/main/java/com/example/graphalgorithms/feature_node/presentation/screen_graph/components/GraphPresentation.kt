package com.example.graphalgorithms.feature_node.presentation.screen_graph.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import com.example.graphalgorithms.feature_node.presentation.NodeFeatureViewModel

@Composable
fun GraphPresentation(
    viewModel: NodeFeatureViewModel
){
    EdgePresentation(viewModel)
    NodesPresentation(viewModel)
}
