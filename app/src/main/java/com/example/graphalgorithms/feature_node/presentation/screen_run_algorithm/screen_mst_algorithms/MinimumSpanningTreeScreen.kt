package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_mst_algorithms

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_choose_algorithms_screen.component.OptionSelectionList

@Composable
fun MinimumSpanningTreeScreen(
    viewModel: MSTViewModel,
    onNavigateToScreen:(route:String)->Unit,
    onBackArrowClicked:()->Unit
){
    OptionSelectionList(
        options = viewModel.options,
        onItemSelectedEvent = {
            viewModel.onItemSelectedEvent(it)
        },
        rout = viewModel.getSelectedOption().route,
        onNavigateToAlgorithmsScreen = onNavigateToScreen,
        onBackArrowClicked = {onBackArrowClicked()},
        paddingTop = 36.dp,
        paddingBottom = 36.dp
    )
}