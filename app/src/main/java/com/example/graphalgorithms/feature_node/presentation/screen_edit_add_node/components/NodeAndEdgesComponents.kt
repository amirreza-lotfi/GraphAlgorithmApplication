package com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.graphalgorithms.feature_node.presentation.GraphScreenViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NodeAndEdgesComponents(
    screenViewModel:GraphScreenViewModel,
    nodeLabel:String,
    onValueOfNodeLabelChanged:(it:String)->Unit
) {
    Column {
        TitleOfScreenXXX(screenViewModel.entitiesOfAddEditScreen.value.titleOfAddEditScreen)
        Column(
            Modifier.padding(8.dp)
        ){
            LabelTextField(
                value = nodeLabel,
                onValueChange = {
                    onValueOfNodeLabelChanged(it)
                },
                typeOfScreen = screenViewModel.entitiesOfAddEditScreen.value.titleOfAddEditScreen
            )
            AddEdgeRow(screenViewModel)
        }
        Spacer(Modifier.height(32.dp))
        EdgesOfNodeComposable(screenViewModel)
    }
}