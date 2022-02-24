package com.example.graphalgorithms.feature_node.presentation.screen_graph

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.graphalgorithms.MainActivity
import com.example.graphalgorithms.MainActivity.Companion
import com.example.graphalgorithms.MainActivity.Companion.ADD_NODE_SCREEN_ROUT
import com.example.graphalgorithms.feature_node.presentation.NodeFeatureViewModel
import com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.util.AddEditNodeScreenEvent
import com.example.graphalgorithms.feature_node.presentation.screen_graph.components.AddEditScreenNavigationButtons
import com.example.graphalgorithms.feature_node.presentation.screen_graph.components.AlgorithmScreenNavigationButton
import com.example.graphalgorithms.feature_node.presentation.screen_graph.components.GraphPresentation
import com.example.graphalgorithms.feature_node.presentation.screen_graph.util.ScreenGraphEvent
import com.example.graphalgorithms.feature_node.presentation.ui.theme.lightGray

@Composable
fun DrawingGraphScreen(
    navController: NavController,
    viewModel: NodeFeatureViewModel = hiltViewModel(),
){
    val isNodeSelected = viewModel.isAnyNodeSelected
    val runAlgorithmButtonVisibility by rememberSaveable{
        mutableStateOf(viewModel.runAlgorithmButtonVisibility)
    }
    Box(
        modifier = Modifier
            .background(lightGray)
            .fillMaxSize()
            .padding(12.dp)
    ){
        AddEditScreenNavigationButtons(
            modifier = Modifier.align(Alignment.BottomEnd),
            isNodeSelected = isNodeSelected.value,
            onAddNodeClicked = {
                viewModel.onScreenGraphEvent(ScreenGraphEvent.OnNavigateToAddScreen)
                navController.navigate(ADD_NODE_SCREEN_ROUT)
            },
            onEditNodeClicked = {
                viewModel.onScreenGraphEvent(ScreenGraphEvent.OnNavigateToEditScreen)
                navController.navigate(ADD_NODE_SCREEN_ROUT)
            })

        if(runAlgorithmButtonVisibility.value) {
            AlgorithmScreenNavigationButton(
                Modifier.align(Alignment.BottomCenter),
                onClick = {
                    viewModel.onScreenGraphEvent(ScreenGraphEvent.OnNavigateToRunAlgorithms)
                    navController.navigate(MainActivity.CHOOSE_ALGORITHMS_SCREEN_ROUT)
                }
            )
        }

        GraphPresentation(viewModel = viewModel)

        if(isNodeSelected.value){
            Button(onClick = {
                viewModel.onScreenGraphEvent(ScreenGraphEvent.DeleteSelectedNode)
            }) {
                Text("Delete", color = Color.White)
            }
        }
    }

}