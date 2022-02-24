package com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import com.example.graphalgorithms.feature_node.presentation.NodeFeatureViewModel
import com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.util.AddEdgeEvent
import com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.util.AddEditNodeScreenEvent
import com.example.graphalgorithms.feature_node.presentation.NodeFeatureViewModel.Companion.hasNoNodeInGraph

@ExperimentalComposeUiApi
@Composable
fun AddEdgeRow(
    viewModel: NodeFeatureViewModel,
){
    var isAddButtonSelected by rememberSaveable{ mutableStateOf(false)}

    Spacer(modifier = Modifier.height(16.dp))
    Column {
        if (!hasNoNodeInGraph()) {
            Button(
                onClick = {
                    isAddButtonSelected = !isAddButtonSelected
                },
                modifier = Modifier.fillMaxWidth()
            ){
                if(isAddButtonSelected){
                    Text("Cancel")
                }else{
                    Text("Add Edge")
                }
            }

            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val nodesLabels = NodeFeatureViewModel.getNodeLabels()

                for(edge: Edge in viewModel.addEditScreenEntity.value.edges){
                    nodesLabels.remove(edge.nodeTo.label)
                    nodesLabels.remove(edge.nodeFrom.label)
                }

                if (isAddButtonSelected && nodesLabels.size>0) {
                    AddEdgeRowComponents(
                        viewModel,
                        nodesLabels,
                        onClick = {isAddButtonSelected = !isAddButtonSelected}
                    )
                }
            }
        }
    }
}

