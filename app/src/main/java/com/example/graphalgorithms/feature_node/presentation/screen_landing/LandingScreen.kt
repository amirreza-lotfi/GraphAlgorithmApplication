package com.example.graphalgorithms.feature_node.presentation.screen_landing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.graphalgorithms.feature_node.presentation.ui.theme.teal
import kotlinx.coroutines.delay

@Composable
fun LandingScreen(
    onTimeout:()->Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(teal),
        contentAlignment = Alignment.Center
    ){
        val currentOnTimeout by rememberUpdatedState(onTimeout)

        LaunchedEffect(true){
            delay(4000L)
            currentOnTimeout()
        }

        Text("Graph Algorithm",style = MaterialTheme.typography.h3, color = Color.White)
    }
}