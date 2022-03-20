package com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node


import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.util.AddEditNodeScreenEvent
import com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.util.UiEvent
import com.example.graphalgorithms.feature_node.presentation.NodeFeatureViewModel
import com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.components.*
import kotlinx.coroutines.flow.collectLatest

@ExperimentalComposeUiApi
@Composable
fun AddEditNodeScreen(
    navController: NavController,
    viewModel: NodeFeatureViewModel
){
    val context = LocalContext.current

    var nodeLabel = viewModel.entitiesOfAddEditScreen.value.nodeLabel


    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        NodeAndEdgesComponents(
            viewModel = viewModel,
            nodeLabel = nodeLabel,
            onValueOfNodeLabelChanged = {
                viewModel.onAddEditScreenEvent(AddEditNodeScreenEvent.OnNodeLabelChanged(it))
            }
        )
        AddEditScreenFloatingActionButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            onClick = {
                viewModel.onAddEditScreenEvent(AddEditNodeScreenEvent.OnSaveNodeButtonClicked)
            }
        )

        LaunchedEffect(key1 = true){
            viewModel.uiEventFlow.collectLatest{ event->
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
        viewModel.onAddEditScreenEvent(AddEditNodeScreenEvent.OnCancelEditNodeButtonClicked)
        navController.popBackStack()
    }
}