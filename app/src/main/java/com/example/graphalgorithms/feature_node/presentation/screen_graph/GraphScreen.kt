package com.example.graphalgorithms.feature_node.presentation.screen_graph

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.graphalgorithms.R
import com.example.graphalgorithms.feature_node.presentation.ScreenGraphViewModel
import com.example.graphalgorithms.feature_node.presentation.screen_graph.components.AddEditScreenNavigationButtons
import com.example.graphalgorithms.feature_node.presentation.screen_graph.components.ActionButtonOfGraph
import com.example.graphalgorithms.feature_node.presentation.screen_graph.components.GraphPresentation
import com.example.graphalgorithms.feature_node.presentation.screen_graph.util.GraphScreenUiEvent
import com.example.graphalgorithms.feature_node.presentation.screen_graph.util.ScreenGraphEvent
import com.example.graphalgorithms.feature_node.presentation.ui.theme.darkGray
import com.example.graphalgorithms.feature_node.presentation.ui.theme.lightGray
import com.example.graphalgorithms.feature_node.presentation.ui.theme.red
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun GraphScreen(
    viewModel: ScreenGraphViewModel,
    onNavigateToAddEditScreen:()->Unit,
    onNavigateToChooseAlgorithmScreen:()->Unit,
){
    val isNodeSelected = viewModel.isAnyNodeSelected
    val actionButtonVisibility by rememberSaveable{
        mutableStateOf(viewModel.runAlgorithmButtonVisibility)
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
                viewModel.onScreenGraphEvent(ScreenGraphEvent.OnNavigateToAddScreen)
                onNavigateToAddEditScreen()
            },
            onEditNodeClicked = {
                viewModel.onScreenGraphEvent(ScreenGraphEvent.OnNavigateToEditScreen)
                onNavigateToAddEditScreen()
            })


        GraphPresentation(viewModel = viewModel)
        if(viewModel.graphEmptyImageVisibility.value){
            Column(
                Modifier.align(Alignment.Center)
            ) {
                Image(
                    painterResource(R.drawable.empty),
                    contentDescription = "The graph is empty",
                    contentScale = ContentScale.Fit
                )
                Text(text = "The graph is empty!!", color = darkGray, modifier = Modifier.align(CenterHorizontally))
            }
        }

        if(isNodeSelected.value){
            ActionButtonOfGraph(
                modifier = modifierOfActionButton
                    .align(Alignment.BottomCenter),
                text = "Delete Node",
                buttonColor = red,
                isButtonVisible = actionButtonVisibility.value,
                onClick = {
                    viewModel.onScreenGraphEvent(ScreenGraphEvent.DeleteSelectedNode)
                    viewModel.reDrawNodes.value+=1
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
                        viewModel.saveGraphInDatabase()
                        onNavigateToChooseAlgorithmScreen()
                    }
                }
            )
        }

        LaunchedEffect(key1 = 1){
            viewModel.graphScreenUiEvent.collectLatest {
                when(it){
                    is GraphScreenUiEvent.OnDataFetched -> {
                        viewModel.onScreenGraphEvent(ScreenGraphEvent.OnSetGraphPictureVisibility)
                    }
                }
            }
        }
    }

}