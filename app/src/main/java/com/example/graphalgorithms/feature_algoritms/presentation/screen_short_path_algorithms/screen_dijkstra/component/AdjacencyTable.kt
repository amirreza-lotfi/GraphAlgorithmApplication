package com.example.graphalgorithms.feature_algoritms.presentation.screen_short_path_algorithms.screen_dijkstra.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.graphalgorithms.feature_algoritms.presentation.screen_short_path_algorithms.screen_dijkstra.util.AdjacentOfNode

@Composable
fun AdjacencyTable(
    modifier: Modifier,
    labels:List<Any>,
    visibility:List<Any>,
    distances:List<Any>,
    lastNode:List<Any>
){

    Box(
        modifier = modifier
    ) {
        Column {
            LazyRowOfAdjacencyTable(array = labels)
            LazyRowOfAdjacencyTable(array = visibility)
            LazyRowOfAdjacencyTable(array = distances)
            LazyRowOfAdjacencyTable(array = lastNode)
        }
    }
}

@Composable
fun LazyRowOfAdjacencyTable(
    array:List<Any>,
){
    LazyRow {
        itemsIndexed(array){_, item ->
            Box(modifier = Modifier
                .size(20.dp)
                .border(1.dp, Color.Black)) {
                Text(item.toString(), Modifier.fillMaxSize(),textAlign = TextAlign.Center)
            }
        }
    }
}
