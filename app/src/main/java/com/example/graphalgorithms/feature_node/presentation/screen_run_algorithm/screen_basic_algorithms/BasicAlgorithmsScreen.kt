package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_basic_algorithms

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_basic_algorithms.components_of_basic_algorithms.component.NonRunnableAlgorithms
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_basic_algorithms.components_of_basic_algorithms.component.RunnableWithStarterNodeAlgorithms
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_choose_algorithms_screen.component.TitleOfScreen


@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun BasicAlgorithmsScreen(
    viewModel: BasicAlgorithmsViewModel,
    onBackArrowClicked:()->Unit
) {

    val scrollState = rememberScrollState()

    Box(
        Modifier.fillMaxSize()
            .background(MaterialTheme.colors.background)
    ){
        Column(
            Modifier.padding(
                top = 8.dp,
                start = 16.dp,
                end = 16.dp
            ).verticalScroll(scrollState)
        ) {
            TitleOfScreen(
                text = "Basic Algorithms",
                onBackArrowClicked = {onBackArrowClicked()}
            )

            NonRunnableAlgorithms(question = "Has graph cycle?", answer = viewModel.hasGraphCycle.value)
            NonRunnableAlgorithms(question = "Has Graph a Tree ?", answer = viewModel.isGraphTree.value)
            NonRunnableAlgorithms(question = "Is Graph Connected?", answer = viewModel.isGraphConnected.value)
            NonRunnableAlgorithms(question = "Is Graph Complete?", answer = viewModel.isGraphComplete.value)
        }
    }
}
