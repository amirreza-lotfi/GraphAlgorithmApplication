package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_short_path_algorithms.screen_choose_starting_node

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_basic_algorithms.components_of_basic_algorithms.component.NodesDropDown
import com.example.graphalgorithms.feature_node.presentation.GraphScreenViewModel
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_basic_algorithms.screen_bfs_traversal.component.TitleOfAlgorithmScreen

@Composable
fun ChooseStartingNodeScreen(
    navController: NavController,
    onNavigateToDijkstraScreen:(startingNode:String)->Unit,
) {
    val nodeLabels = GraphScreenViewModel.getNodeLabels()
    val selectedIndex = rememberSaveable{ mutableStateOf(0)}
    val startNodeLabel = rememberSaveable{ mutableStateOf(nodeLabels[0])}

    Box(
        Modifier.fillMaxSize()
            .padding(16.dp)
    ) {
        TitleOfAlgorithmScreen(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .align(Alignment.TopStart),
            onBackClicked = { navController.popBackStack() },
            onDescriptionClicked = {/*Todo*/}
        )

        Row(
            Modifier.padding(start = 24.dp, top = 80.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
           Text(text = "Choose starting node")
           Spacer(modifier = Modifier.width(10.dp))
           NodesDropDown(nodesLabels = nodeLabels, selectedIndex = selectedIndex, startNodeLabel = startNodeLabel)
        }

        Button(
            onClick = {
                onNavigateToDijkstraScreen(startNodeLabel.value)
            },
            modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth()
        ) {
            Text("Start Algorithm")
        }
    }

}