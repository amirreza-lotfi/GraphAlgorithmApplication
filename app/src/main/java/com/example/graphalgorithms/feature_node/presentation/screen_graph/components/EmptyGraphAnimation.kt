package com.example.graphalgorithms.feature_node.presentation.screen_graph.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.graphalgorithms.R
import com.example.graphalgorithms.feature_node.presentation.GraphScreenViewModel
import com.example.graphalgorithms.feature_node.presentation.ui.theme.darkGray

@Composable
fun EmptyGraphAnimation(
    modifier: Modifier,
    screenViewModel:GraphScreenViewModel
) {
    if(screenViewModel.graphEmptyImageVisibility.value){
        Column(
            modifier = modifier
        ) {
            Image(
                painterResource(R.drawable.empty),
                contentDescription = "The graph is empty",
                contentScale = ContentScale.Fit
            )
            Text(text = "The graph is empty!!", color = darkGray, modifier = Modifier.align(
                Alignment.CenterHorizontally
            ))
        }
    }
}