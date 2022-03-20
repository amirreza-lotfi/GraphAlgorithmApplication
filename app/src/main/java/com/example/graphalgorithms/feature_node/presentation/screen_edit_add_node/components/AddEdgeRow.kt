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
import com.example.graphalgorithms.feature_node.domain.entitiy.EdgeWithLabels
import com.example.graphalgorithms.feature_node.presentation.NodeFeatureViewModel
import com.example.graphalgorithms.feature_node.presentation.NodeFeatureViewModel.Companion.hasNoNodeInGraph
import com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.util.AddEditNodeScreenEvent

@ExperimentalComposeUiApi
@Composable
fun AddEdgeRow(
    viewModel: NodeFeatureViewModel,
){
    val isAddButtonSelected = viewModel.isAddButtonSelected.value

    val nodesLabels = NodeFeatureViewModel.getNodeLabels()
    val availableNodesInEdgeList = mutableListOf<String>()

    for(edge: EdgeWithLabels in viewModel.entitiesOfAddEditScreen.value.edges){
        availableNodesInEdgeList.add(edge.toLabel)
    }
    availableNodesInEdgeList.add(viewModel.entitiesOfAddEditScreen.value.nodeLabel)
    nodesLabels.removeAll(availableNodesInEdgeList)

    Spacer(modifier = Modifier.height(16.dp))
    Column {
        if (!hasNoNodeInGraph()) {
            Button(
                onClick = {
                    viewModel.onAddEditScreenEvent(AddEditNodeScreenEvent.OnAddEdgeRowSelection)
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
                        viewModel,
                        nodesLabels,
                        onClick = {
                            viewModel.onAddEditScreenEvent(AddEditNodeScreenEvent.OnAddEdgeRowSelection)
                        }
                    )
                }
            }
        }
    }
}

