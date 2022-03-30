package com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.graphalgorithms.feature_node.presentation.ui.theme.darkGray
import com.example.graphalgorithms.feature_node.presentation.ui.theme.lightGray

@Composable
fun EdgeSecondNodeDropDown(
    nodesLabels:List<String>,
    selectedIndex:MutableState<Int>,
    toLabel: MutableState<String>
){
    var expanded by remember { mutableStateOf(false) }


    Box {
            Column(
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .padding(all = 0.dp),
            ) {

                Button(
                    modifier = Modifier.size(56.dp, 36.dp),
                    border = BorderStroke(width = 0.3.dp, color = darkGray),
                    colors = ButtonDefaults.buttonColors(backgroundColor = lightGray),
                    onClick = {
                        expanded = true
                    }, content = {
                        Text(nodesLabels[selectedIndex.value])
                    })

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .background(Color.White)
                        .width(50.dp),
                ) {
                    nodesLabels.forEachIndexed { index, s ->
                        DropdownMenuItem(onClick = {
                            selectedIndex.value = index
                            toLabel.value = nodesLabels[selectedIndex.value]
                            expanded = false
                        }) {
                            Text(text = s)
                        }
                    }
                }
            }
        }
}
