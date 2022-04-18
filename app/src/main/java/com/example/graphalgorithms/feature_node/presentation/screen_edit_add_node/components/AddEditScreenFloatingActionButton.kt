package com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.util.AddEditNodeScreenEvent

@Composable
fun AddEditScreenFloatingActionButton(
    modifier: Modifier,
    onClick:()->Unit
) {
    FloatingActionButton(
        onClick = {
            onClick()
        }
        , modifier = modifier
    ) {
        Icon(Icons.Filled.Save, tint = Color.White, contentDescription = "save")
    }
}