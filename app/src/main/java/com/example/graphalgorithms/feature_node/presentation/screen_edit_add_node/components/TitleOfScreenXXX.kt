package com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TitleOfScreenXXX(str:String){
    Row {
        Icon(Icons.Filled.ArrowBack, contentDescription = "back to Graph screen", Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(24.dp))
        Text(str, style = MaterialTheme.typography.h6)
    }
    Spacer(Modifier.height(32.dp))
}