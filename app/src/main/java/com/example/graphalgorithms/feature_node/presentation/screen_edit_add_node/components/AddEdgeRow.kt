package com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.graphalgorithms.feature_node.domain.entitiy.EdgeWithLabels
import com.example.graphalgorithms.feature_node.presentation.GraphScreenViewModel
import com.example.graphalgorithms.feature_node.presentation.GraphScreenViewModel.Companion.hasNoNodeInGraph
import com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.util.AddEditNodeScreenEvent

@ExperimentalComposeUiApi
@Composable
fun AddEdgeRow(
    screenViewModel: GraphScreenViewModel,
){
    val isAddButtonSelected = screenViewModel.isAddButtonSelected.value

    val nodesLabels = GraphScreenViewModel.getNodeLabels()
    val availableNodesInEdgeList = mutableListOf<String>()

    for(edge: EdgeWithLabels in screenViewModel.entitiesOfAddEditScreen.value.edges){
        availableNodesInEdgeList.add(edge.toLabel)
    }
    availableNodesInEdgeList.add(screenViewModel.entitiesOfAddEditScreen.value.nodeLabel)
    nodesLabels.removeAll(availableNodesInEdgeList)

    Spacer(modifier = Modifier.height(16.dp))
    Column {
        if (!hasNoNodeInGraph()) {
            Button(
                onClick = {
                    screenViewModel.onAddEditScreenEvent(AddEditNodeScreenEvent.OnAddEdgeRowSelection)
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
                if (isAddButtonSelected && nodesLabels.size>0) {
                    LabelAndAddEdgeComponents(
                        screenViewModel,
                        nodesLabels,
                        onClick = {
                            screenViewModel.onAddEditScreenEvent(AddEditNodeScreenEvent.OnAddEdgeRowSelection)
                        }
                    )
                }
            }
        }
    }
}

