package com.example.graphalgorithms.feature_node.presentation.screen_graph.components

import androidx.compose.runtime.Composable
import com.example.graphalgorithms.feature_node.presentation.GraphScreenViewModel

@Composable
fun GraphPresentation(
    screenViewModel: GraphScreenViewModel
){
    EdgePresentation(screenViewModel)
    NodesPresentation(screenViewModel)
}
