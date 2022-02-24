package com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import com.example.graphalgorithms.feature_node.presentation.NodeFeatureViewModel
import com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.util.AddEdgeEvent
import com.example.graphalgorithms.feature_node.presentation.ui.theme.lightYellow

@Composable
fun EdgesOfNodeComposable(
    viewModelEdit: NodeFeatureViewModel,
){
    val edgesOfNode = viewModelEdit.addEditScreenEntity.value.edges
    val recomposition = viewModelEdit.counterForRecomposition

    if(recomposition.value>0) {
        LazyColumn {
            items(edgesOfNode) { edge ->
                EdgeComposable(
                    viewModelEdit,
                    edge = edge,
                    onDismissIconClicked = {
                        viewModelEdit.onEdgeEvent(AddEdgeEvent.OnDeleteEdgeClicked(edge))
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}


@Composable
fun EdgeComposable(
    viewModelEdit: NodeFeatureViewModel,
    edge: Edge,
    onDismissIconClicked:()->Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(CircleShape)
            .background(lightYellow)
    ){
        val labelTo = if(
            viewModelEdit.addEditScreenEntity.value.newNode.label == edge.nodeTo.label)
                edge.nodeFrom.label else edge.nodeTo.label
        Text(" Edge To : $labelTo    Weight : ${edge.weight}",
            Modifier
                .padding(start = 24.dp)
                .align(Alignment.CenterVertically),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.width(16.dp))
        Icon(
            Icons.Filled.Delete,
            contentDescription = "remove edge",
            Modifier
                .clickable {
                    onDismissIconClicked()
                }
                .align(Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.width(24.dp))
    }
}
