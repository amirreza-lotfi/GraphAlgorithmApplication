package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_short_path_algorithms.screen_dijkstra.component

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_short_path_algorithms.screen_dijkstra.util.AdjacencyTable

@Composable
fun AdjacencyTableComposable(
    modifier: Modifier,
    labels:List<Any>,
    adjacencyTable: AdjacencyTable
){
    val scrollState = rememberScrollState()
    Box(
        modifier = modifier
    ) {
        Column{
            RowOfAdjacencyTable(array = labels, scrollState)
            RowOfAdjacencyTable(
                array = adjacencyTable.visibilityArray.toList(),
                scrollState = scrollState
            )
            RowOfAdjacencyTable(
                array = adjacencyTable.distances.toList(),
                scrollState = scrollState
            )
            RowOfAdjacencyTable(
                array = adjacencyTable.lastNodeArray.toList(),
                scrollState = scrollState
            )
        }
    }
}

@Composable
fun RowOfAdjacencyTable(
    array: List<Any>,
    scrollState: ScrollState,
){
    Row(
        Modifier.horizontalScroll(scrollState)
    ) {
        for(item:Any in array){
            var _item = item

            if(item.javaClass.typeName == "java.lang.Float"){
                if (item == Float.POSITIVE_INFINITY){
                    _item = "∞"
                }
            }
            Box(modifier = Modifier
                .width(50.dp)
                .height(30.dp)
                .border(1.dp, Color.Black)) {
                Text(_item.toString(), Modifier.fillMaxSize(),textAlign = TextAlign.Center)
            }
        }
    }
}
