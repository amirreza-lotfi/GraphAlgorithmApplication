package com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.graphalgorithms.feature_node.presentation.NodeFeatureViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NodeAndEdgesComponents(
    viewModel:NodeFeatureViewModel,
    nodeLabel:String,
    onValueOfNodeLabelChanged:(it:String)->Unit
) {
    Column {
        TitleOfScreenXXX(viewModel.entitiesOfAddEditScreen.value.titleOfAddEditScreen)
        Column(
            Modifier.padding(8.dp)
        ){
            LabelTextField(
                value = nodeLabel,
                onValueChange = {
                    onValueOfNodeLabelChanged(it)
                },
                typeOfScreen = viewModel.entitiesOfAddEditScreen.value.titleOfAddEditScreen
            )
            AddEdgeRow(viewModel)
        }
        Spacer(Modifier.height(32.dp))
        EdgesOfNodeComposable(viewModel)
    }
}