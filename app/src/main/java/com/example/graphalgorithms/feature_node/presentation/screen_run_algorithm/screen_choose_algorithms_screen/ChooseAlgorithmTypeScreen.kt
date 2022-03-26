package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_choose_algorithms_screen
import androidx.compose.runtime.*
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_choose_algorithms_screen.component.OptionSelectionList


@Composable
fun ChooseAlgorithmTypeScreen(
    viewModel: ChooseAlgorithmsTypeViewModel,
    onNavigateToAlgorithmsScreen:(rout:String)->Unit,
    onBackArrowClicked:()->Unit
){
    OptionSelectionList(
        options = viewModel.options,
        onItemSelectedEvent = { index->
            viewModel.onItemSelectedEvent(index)
        },
        rout = viewModel.getSelectedOption().route,
        onNavigateToAlgorithmsScreen = onNavigateToAlgorithmsScreen,
        onBackArrowClicked = {onBackArrowClicked()}
    )
}
