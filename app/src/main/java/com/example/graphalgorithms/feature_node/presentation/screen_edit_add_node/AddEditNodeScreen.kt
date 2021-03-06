package com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node


import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.util.AddEditNodeScreenEvent
import com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.util.UiEvent
import com.example.graphalgorithms.feature_node.presentation.GraphScreenViewModel
import com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.components.*
import kotlinx.coroutines.flow.collectLatest

@ExperimentalComposeUiApi
@Composable
fun AddEditNodeScreen(
    navController: NavController,
    screenViewModel: GraphScreenViewModel
){
    val context = LocalContext.current

    var nodeLabel = screenViewModel.entitiesOfAddEditScreen.value.nodeLabel


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(16.dp)
    ) {
        Column {
            TitleOfScreenXXX(screenViewModel.entitiesOfAddEditScreen.value.titleOfAddEditScreen)

            NodeAndEdgesComponents(
                screenViewModel = screenViewModel,
                nodeLabel = nodeLabel,
                onValueOfNodeLabelChanged = {
                    screenViewModel.onAddEditScreenEvent(AddEditNodeScreenEvent.OnNodeLabelChanged(it))
                }
            )
        }

        AddEditScreenFloatingActionButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            onClick = {
                screenViewModel.onAddEditScreenEvent(AddEditNodeScreenEvent.OnSaveNodeButtonClicked)
            }
        )

        LaunchedEffect(key1 = true){
            screenViewModel.uiEventFlow.collectLatest{ event->
                when(event){
                    is UiEvent.ShowErrorSnackbar->{
                        Toast.makeText(context,event.str,Toast.LENGTH_LONG).show()
                    }
                    is UiEvent.SaveNode -> {
                        navController.popBackStack()
                    }
                }
            }
        }
    }

    BackHandler {
        screenViewModel.onAddEditScreenEvent(AddEditNodeScreenEvent.OnCancelEditNodeButtonClicked)
        navController.popBackStack()
    }
}