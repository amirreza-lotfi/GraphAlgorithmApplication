package com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node


import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.components.AddEdgeRow
import com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.components.EdgesOfNodeComposable
import com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.components.LabelTextField
import com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.components.TitleOfScreen
import com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.util.AddEditNodeScreenEvent
import com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.util.UiEvent
import com.example.graphalgorithms.feature_node.presentation.NodeFeatureViewModel
import kotlinx.coroutines.flow.collectLatest

@ExperimentalComposeUiApi
@Composable
fun AddEditNodeScreen(
    navController: NavController,
    viewModel: NodeFeatureViewModel
){
    var nodeLabel by rememberSaveable {
        mutableStateOf(viewModel.addEditScreenEntity.value.newNode.label)
    }

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Column {
            TitleOfScreen(viewModel.addEditScreenEntity.value.titleOfAddEditScreen)
            Column(
                Modifier.padding(8.dp)
            ){
                LabelTextField(
                    value = nodeLabel,
                    onValueChange = {
                        nodeLabel = it
                    }
                )
                AddEdgeRow(viewModel)
            }
            Spacer(Modifier.height(32.dp))
            EdgesOfNodeComposable(viewModel)
        }

        FloatingActionButton(
            onClick = {
                    viewModel.onAddEditScreenEvent(AddEditNodeScreenEvent.OnSaveEditNodeButtonClicked(nodeLabel))
                },Modifier.align(Alignment.BottomEnd)) {
            Icon(Icons.Filled.Save, contentDescription = "save")
        }

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