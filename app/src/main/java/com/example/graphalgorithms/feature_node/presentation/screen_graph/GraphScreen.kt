package com.example.graphalgorithms.feature_node.presentation.screen_graph

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.graphalgorithms.feature_node.presentation.GraphScreenViewModel
import com.example.graphalgorithms.feature_node.presentation.screen_graph.components.AddEditScreenNavigationButtons
import com.example.graphalgorithms.feature_node.presentation.screen_graph.components.ActionButtonOfGraph
import com.example.graphalgorithms.feature_node.presentation.screen_graph.components.EmptyGraphAnimation
import com.example.graphalgorithms.feature_node.presentation.screen_graph.components.GraphPresentation
import com.example.graphalgorithms.feature_node.presentation.screen_graph.util.GraphScreenUiEvent
import com.example.graphalgorithms.feature_node.presentation.screen_graph.util.ScreenGraphEvent
import com.example.graphalgorithms.feature_node.presentation.ui.theme.lightGray
import com.example.graphalgorithms.feature_node.presentation.ui.theme.red
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun GraphScreen(
    screenViewModel: GraphScreenViewModel,
    onNavigateToAddEditScreen:()->Unit,
    onNavigateToChooseAlgorithmScreen:()->Unit,
){
    val isNodeSelected = screenViewModel.isAnyNodeSelected
    val actionButtonVisibility by rememberSaveable{
        mutableStateOf(screenViewModel.runAlgorithmButtonVisibility)
    }

    val modifierOfActionButton = Modifier
        .padding(bottom = 4.dp)
        .width(176.dp)
        .height(48.dp)
        .clip(RoundedCornerShape(8.dp))

    val coroutineScope = rememberCoroutineScope()

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
                screenViewModel.onScreenGraphEvent(ScreenGraphEvent.OnNavigateToAddScreen)
                onNavigateToAddEditScreen()
            },
            onEditNodeClicked = {
                screenViewModel.onScreenGraphEvent(ScreenGraphEvent.OnNavigateToEditScreen)
                onNavigateToAddEditScreen()
            })


        GraphPresentation(screenViewModel = screenViewModel)

        EmptyGraphAnimation(modifier = Modifier.align(Alignment.Center), screenViewModel = screenViewModel)

        if(isNodeSelected.value){
            ActionButtonOfGraph(
                modifier = modifierOfActionButton
                    .align(Alignment.BottomCenter),
                text = "Delete Node",
                buttonColor = red,
                isButtonVisible = actionButtonVisibility.value,
                onClick = {
                    screenViewModel.onScreenGraphEvent(ScreenGraphEvent.DeleteSelectedNode)
                    screenViewModel.reDrawNodes.value+=1
                }
            )
        }else{
            ActionButtonOfGraph(
                modifier = modifierOfActionButton
                    .align(Alignment.BottomCenter),
                text = "Run Algorithm",
                buttonColor = MaterialTheme.colors.primary,
                isButtonVisible = actionButtonVisibility.value,
                onClick = {
                    coroutineScope.launch {
                        screenViewModel.saveGraphInDatabase()
                        onNavigateToChooseAlgorithmScreen()
                    }
                }
            )
        }

        LaunchedEffect(key1 = 1){
            screenViewModel.graphScreenUiEvent.collectLatest {
                when(it){
                    is GraphScreenUiEvent.OnDataFetched -> {
                        screenViewModel.onScreenGraphEvent(ScreenGraphEvent.OnSetGraphPictureVisibility)
                    }
                }
            }
        }
    }

}