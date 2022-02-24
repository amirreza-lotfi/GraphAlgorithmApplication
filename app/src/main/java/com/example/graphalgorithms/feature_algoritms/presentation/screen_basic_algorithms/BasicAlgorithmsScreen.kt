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
import com.example.graphalgorithms.feature_algoritms.presentation.RunAlgorithmsViewModel
import com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.components_of_basic_algorithms.component.NonRunnableAlgorithms
import com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.components_of_basic_algorithms.component.RunnableWithStarterNodeAlgorithms
import com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.components_of_basic_algorithms.component.TitleOfAlgorithmsType
import com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.components_of_basic_algorithms.util.BasicAlgorithmsEvent
import kotlinx.coroutines.launch


@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun BasicAlgorithmsScreen(
    navController: NavController,
    viewModel: BasicAlgorithmsViewModel = BasicAlgorithmsViewModel(
        hiltViewModel(
        navController.getBackStackEntry("ChooseAlgorithmsScreen")
        )
    )
) {

    val viewModelScope = rememberCoroutineScope()
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
                viewModelScope.launch {
                    navController.navigate("BFSTraversalScreen")
                    viewModel.onEvent(BasicAlgorithmsEvent.OnNavigateToBFSScreen)
                }
            }
            RunnableWithStarterNodeAlgorithms(algorithmsName = "DFS algorithm",viewModel= viewModel) {
                viewModelScope.launch {
                    navController.navigate("DFSTraversalScreen")
                    viewModel.onEvent(BasicAlgorithmsEvent.OnNavigateToDFSScreen)
                }
            }
            RunnableWithStarterNodeAlgorithms(algorithmsName = "Find mother vertex in a graph",viewModel= viewModel) {
                //todo find mother
            }
            NonRunnableAlgorithms(question = "Is graph connected?", answer = "")
            NonRunnableAlgorithms(question = "Is graph complete?", answer = "")
            NonRunnableAlgorithms(question = "Is graph monotonic?", answer = "")
            NonRunnableAlgorithms(question = "Has graph cycle?", answer = "")
            NonRunnableAlgorithms(question = "Count number of trees in a forest : ", answer = "")
        }
    }


}
/**
 *   is graph complete?
 *   is graph hamband?
 *   is graph yeknavakht?
 *   has graph cycle?
 *   number of nodes in each level. %
 *   BFS traversal
 *   DFS traversal
 *   find mother vertex in a graph
 *   Count all possible paths between two vertices %
 *   Count number of trees in a forest
 *   Transpose graph
* */