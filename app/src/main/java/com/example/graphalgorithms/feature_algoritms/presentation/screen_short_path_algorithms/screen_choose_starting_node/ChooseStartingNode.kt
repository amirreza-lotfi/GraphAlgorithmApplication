package com.example.graphalgorithms.feature_algoritms.presentation.screen_short_path_algorithms.screen_choose_starting_node

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.components_of_basic_algorithms.component.NodesDropDown
import com.example.graphalgorithms.feature_node.presentation.NodeFeatureViewModel

@Composable
fun ChooseStartingNodeScreen(
    onNavigateToDijkstraScreen:(startingNode:String)->Unit,
) {
    val nodeLabels = NodeFeatureViewModel.getNodeLabels()
    val selectedIndex = rememberSaveable{ mutableStateOf(0)}
    val startNodeLabel = rememberSaveable{ mutableStateOf(nodeLabels[0])}

    Box(
        Modifier.fillMaxSize()
            .padding(36.dp)
    ) {
        Row(
            Modifier.padding(24.dp),
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

@Preview
@Composable
fun test() {
    ChooseStartingNodeScreen {

    }
}