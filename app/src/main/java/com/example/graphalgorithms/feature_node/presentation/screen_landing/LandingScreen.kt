package com.example.graphalgorithms.feature_node.presentation.screen_landing

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*
import com.example.graphalgorithms.R
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

        var isPlaying by remember {
            mutableStateOf(true)
        }

        var speed by remember {
            mutableStateOf(0.2f)
        }

        LaunchedEffect(Unit) {
            delay(3000L)
            onTimeout()
        }

        val composition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(R.raw.abc)
        )

        val progress = animateLottieCompositionAsState(
            composition,
            iterations = LottieConstants.IterateForever,
            isPlaying = isPlaying,
            speed = speed,
            restartOnPlay = false,
        )


        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Graph Algorithm", fontSize = 40.sp, color = Color.White)
            LottieAnimation(
                composition,
                progress.progress,
                Modifier.size(400.dp)
            )
        }

    }
}