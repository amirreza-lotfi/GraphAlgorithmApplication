package com.example.graphalgorithms.feature_algoritms.presentation.screen_short_path_algorithms

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.graphalgorithms.MainActivity
import com.example.graphalgorithms.feature_algoritms.presentation.screen_choose_algorithms_screen.component.OptionSelectionList

@Composable
fun ShortPathScreen(
    viewModel: ShortPathViewModel,
    onNavigateToScreen:(route:String)->Unit
){
    val rout = viewModel.getSelectedOption().route

    OptionSelectionList(
        options = viewModel.options,
        onItemSelectedEvent = {
            viewModel.onItemSelectedEvent(it)
        },
        rout = "${MainActivity.CHOOSE_STARTING_NODE_SCREEN_ROUT}/$rout",
        onNavigateToAlgorithmsScreen = onNavigateToScreen,
        paddingTop = 36.dp,
        paddingBottom = 36.dp
    )
}