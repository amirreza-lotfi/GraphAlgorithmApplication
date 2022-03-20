package com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.graphalgorithms.feature_node.presentation.NodeFeatureViewModel
import com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.util.AddEditNodeScreenEvent

@ExperimentalComposeUiApi
@Composable
fun LabelAndAddEdgeComponents(
    viewModel: NodeFeatureViewModel,
    nodesLabels:List<String>,
    onClick:()->Unit
) {
    var selectedIndex = remember {mutableStateOf(0)}
    var toLabel = remember {mutableStateOf(nodesLabels[0])}

    //textview -> To
    Box{
        Text("To :",
            Modifier
                .align(Alignment.Center)
                .padding(start = 4.dp))
    }

    Spacer(modifier = Modifier.size(8.dp))

    EdgeSecondNodeDropDown(
        nodesLabels,
        selectedIndex,
        toLabel
    )

    Spacer(modifier = Modifier.size(20.dp))

    //weight -> To
    Box{
        Text("Weight : ", Modifier.align(Alignment.Center))
    }

    Spacer(modifier = Modifier.size(8.dp))

    WeightTextFiled(viewModel)

    Spacer(modifier = Modifier.size(12.dp))

    Box(
        Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = {
                onClick()
                viewModel.onAddEditScreenEvent(
                AddEditNodeScreenEvent.SaveEdgeButtonClicked(toLabel.value))
                viewModel.onAddEditScreenEvent(AddEditNodeScreenEvent.OnWeightChanged(""))
            },
            Modifier
                .height(36.dp)
                .align(Alignment.BottomEnd)
        ) {
            Text("Save")
        }
    }

}