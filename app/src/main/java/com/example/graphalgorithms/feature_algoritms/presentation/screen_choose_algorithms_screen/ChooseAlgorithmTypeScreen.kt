package com.example.graphalgorithms.feature_algoritms.presentation.screen_choose_algorithms_screen
import androidx.compose.material.*
import androidx.compose.runtime.*
import com.example.graphalgorithms.feature_algoritms.presentation.screen_choose_algorithms_screen.component.OptionSelectionList


@Composable
fun ChooseAlgorithmTypeScreen(
    viewModel: ChooseAlgorithmsTypeViewModel,
    onNavigateToAlgorithmsScreen:(rout:String)->Unit,
){
    OptionSelectionList(
        options = viewModel.options,
        onItemSelectedEvent = { index->
            viewModel.onItemSelectedEvent(index)
        },
        rout = viewModel.getSelectedOption().route,
        onNavigateToAlgorithmsScreen = onNavigateToAlgorithmsScreen
    )
}
