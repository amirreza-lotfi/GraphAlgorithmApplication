package com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.graphalgorithms.feature_node.domain.entitiy.EdgeWithLabels
import com.example.graphalgorithms.feature_node.presentation.GraphScreenViewModel
import com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.util.AddEditNodeScreenEvent
import com.example.graphalgorithms.feature_node.presentation.ui.theme.*

@Composable
fun EdgesOfNodeComposable(
    screenViewModelEdit: GraphScreenViewModel,
){
    val edgesOfNode = screenViewModelEdit.entitiesOfAddEditScreen.value.edges
    val recomposition = screenViewModelEdit.counterForRecomposition

    if(recomposition.value>0) {
        LazyColumn {
            items(edgesOfNode) { edge ->

                EdgeComposable(
                    edge = edge,
                    onDismissIconClicked = {
                        screenViewModelEdit.onAddEditScreenEvent(AddEditNodeScreenEvent.OnDeleteEdgeClicked(edge))
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}


@Composable
fun EdgeComposable(
    edge: EdgeWithLabels,
    onDismissIconClicked:()->Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp,MaterialTheme.colors.primary, RoundedCornerShape(8.dp))
    ){
        val labelTo = edge.toLabel

        Text(" Edge To : $labelTo       Weight : ${edge.weight}",
            Modifier
                .padding(start = 24.dp)
                .align(Alignment.CenterVertically),
            fontSize = 18.sp,
            color = black
        )

        Box(
            Modifier.padding(end = 24.dp).fillMaxSize()
        ){
            Icon(
                Icons.Filled.Delete,
                contentDescription = "remove edge",
                Modifier
                    .clickable {
                        onDismissIconClicked()
                    }
                    .align(Alignment.CenterEnd),
            )
        }
    }
}
