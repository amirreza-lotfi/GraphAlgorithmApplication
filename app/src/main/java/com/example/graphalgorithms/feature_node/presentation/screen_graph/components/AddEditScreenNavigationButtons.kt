package com.example.graphalgorithms.feature_node.presentation.screen_graph.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AddEditScreenNavigationButtons(
    modifier: Modifier,
    isNodeSelected:Boolean,
    onEditNodeClicked:()->Unit,
    onAddNodeClicked:()->Unit,
){
    if(isNodeSelected){
        FloatingActionButton(
            onClick = {
                onEditNodeClicked()
            },
            modifier = modifier
        ) {
            Icon(Icons.Filled.Edit, contentDescription = "add node to screen")
        }
    }
    else{
        FloatingActionButton(
            onClick = {
                onAddNodeClicked()
            }, modifier = modifier
        ) {
            Icon(Icons.Filled.Add, contentDescription = "add node to screen")
        }
    }
}