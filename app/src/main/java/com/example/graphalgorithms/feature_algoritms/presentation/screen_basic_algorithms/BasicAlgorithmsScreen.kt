package com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.components_of_basic_algorithms.component.NonRunnableAlgorithms
import com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.components_of_basic_algorithms.component.RunnableWithStarterNodeAlgorithms
import com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.components_of_basic_algorithms.component.TitleOfAlgorithmsType
import com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.components_of_basic_algorithms.util.BasicAlgorithmsEvent
import kotlinx.coroutines.launch


@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun BasicAlgorithmsScreen(
    viewModel: BasicAlgorithmsViewModel,
    onNavigateToBFSScreen: (startingNode:String)->Unit,
    onNavigateToDFSScreen: (startingNode:String) -> Unit
) {

    Box(Modifier.fillMaxSize()){
        Column(
            Modifier.padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp
            ),
        ) {
            TitleOfAlgorithmsType(title = "Basic Algorithms")

            RunnableWithStarterNodeAlgorithms(algorithmsName = "BFS algorithm", viewModel= viewModel) {
                onNavigateToBFSScreen(viewModel.starterNodeForBfsAlgorithms)
            }
            RunnableWithStarterNodeAlgorithms(algorithmsName = "DFS algorithm",viewModel= viewModel) {
                onNavigateToDFSScreen(viewModel.starterNodeForBfsAlgorithms)
            }
            NonRunnableAlgorithms(question = "Has graph cycle?", answer = viewModel.hasGraphCycle.value)
            NonRunnableAlgorithms(question = "Has Graph a Tree ?", answer = viewModel.isGraphTree.value)
            NonRunnableAlgorithms(question = "Is Graph Connected?", answer = viewModel.isGraphConnected.value)
            NonRunnableAlgorithms(question = "Is Graph Complete?", answer = viewModel.isGraphComplete.value)
        }
    }
}
