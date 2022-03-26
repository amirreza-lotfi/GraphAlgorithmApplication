package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_choose_algorithms_screen.component
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TitleOfScreen(
    text:String,
    onBackArrowClicked:()->Unit
) {
    Column{
        Icon(
            Icons.Filled.ArrowBack,
            contentDescription = "back to Graph screen",
            Modifier.size(24.dp)
                .clickable {
                    onBackArrowClicked()
                }
        )
        Column(
            Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, bottom = 30.dp)
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}