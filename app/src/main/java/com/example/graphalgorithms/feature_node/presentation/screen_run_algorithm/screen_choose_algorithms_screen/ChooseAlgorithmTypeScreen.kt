package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_choose_algorithms_screen
import androidx.compose.runtime.*
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_choose_algorithms_screen.component.OptionSelectionList
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_choose_algorithms_screen.util.OptionItem


@Composable
fun ChooseAlgorithmTypeScreen(
    viewModel: ChooseAlgorithmsTypeViewModel,
    onNavigateToAlgorithmsScreen:(rout: OptionItem)->Unit,
    onBackArrowClicked:()->Unit
){
    OptionSelectionList(
        viewModel = viewModel,
        onNavigateToAlgorithmsScreen = onNavigateToAlgorithmsScreen,
        onBackArrowClicked = {onBackArrowClicked()}
    )
}
