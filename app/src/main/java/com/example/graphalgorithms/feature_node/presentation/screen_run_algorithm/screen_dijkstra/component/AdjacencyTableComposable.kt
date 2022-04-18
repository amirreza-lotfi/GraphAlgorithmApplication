package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_dijkstra.component

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_dijkstra.util.AdjacencyTable

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
            RowOfAdjacencyTable(array = labels, scrollState, "Node")
            RowOfAdjacencyTable(
                array = adjacencyTable.visibilityArray.toList(),
                scrollState = scrollState,
                "Visible"
            )
            RowOfAdjacencyTable(
                array = adjacencyTable.distances.toList(),
                scrollState = scrollState,
                "Distance"
            )
            RowOfAdjacencyTable(
                array = adjacencyTable.lastNodeArray.toList(),
                scrollState = scrollState,
                "LastNode"
            )
        }
    }
}

@Composable
fun RowOfAdjacencyTable(
    array: List<Any>,
    scrollState: ScrollState,
    title:String,
){
    Row(
        Modifier.horizontalScroll(scrollState)
    ) {

        Box(modifier = Modifier
            .width(80.dp)
            .height(24.dp)
            .border(1.dp, Color.Black)) {
            Text(title, Modifier.fillMaxSize(),textAlign = TextAlign.Center)
        }

        for(item:Any in array){

            var _item = item

            if(item.javaClass.typeName == "java.lang.Float"){
                if (item == Float.POSITIVE_INFINITY){
                    _item = "∞"
                }
            }
            Box(modifier = Modifier
                .width(40.dp)
                .height(24.dp)
                .border(1.dp, Color.Black)) {
                Text(_item.toString(), Modifier.fillMaxSize(),textAlign = TextAlign.Center)
            }
        }
    }
}
