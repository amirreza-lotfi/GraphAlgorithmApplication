package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_choose_starting_node

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_basic_algorithms.components_of_basic_algorithms.component.NodesDropDown
import com.example.graphalgorithms.feature_node.presentation.GraphScreenViewModel
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_bfs_traversal.component.TitleOfAlgorithmScreen
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_choose_algorithms_screen.component.CustomBackButton

@Composable
fun ChooseStartingNodeScreen(
    navController: NavController,
    onNavigateToDijkstraScreen:(startingNode:String)->Unit,
) {
    val nodeLabels = GraphScreenViewModel.getNodeLabels()
    val selectedIndex = rememberSaveable{ mutableStateOf(0)}
    val startNodeLabel = rememberSaveable{ mutableStateOf(nodeLabels[0])}

    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(start = 16.dp, top = 8.dp)
    ) {
        CustomBackButton {
            navController.popBackStack()
        }

        Row(
            Modifier
                .align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically,
        ){
           Text(text = "Choose starting node")
           Spacer(modifier = Modifier.width(10.dp))
           NodesDropDown(nodesLabels = nodeLabels, selectedIndex = selectedIndex, startNodeLabel = startNodeLabel)
        }

        Button(
            onClick = {
                onNavigateToDijkstraScreen(startNodeLabel.value)
            },
            modifier = Modifier.align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
                .width(176.dp)
                .height(48.dp)
                .clip(RoundedCornerShape(8.dp))
        ) {
            Text("Start Algorithm")
        }
    }

}