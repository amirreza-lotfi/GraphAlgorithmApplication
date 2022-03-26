package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_basic_algorithms.components_of_basic_algorithms.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.graphalgorithms.feature_node.presentation.ui.theme.lightGray

@Composable
fun NonRunnableAlgorithms(
    question:String,
    answer:String
){
    Box(
        Modifier
            .padding(top = 8.dp)
            .height(56.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(lightGray)
            .padding(top = 8.dp, bottom = 8.dp, end = 40.dp, start = 16.dp),
    ) {
        Row(
            Modifier.align(Alignment.CenterStart),
        ) {
            Text(
                text = question,
                Modifier.align(Alignment.CenterVertically),
                style = MaterialTheme.typography.body1
            )
            Spacer(modifier = Modifier.width(16.dp))
        }
        Text(
            text = answer,
            Modifier.align(Alignment.CenterEnd),
            style = MaterialTheme.typography.body1
        )
    }
}
@Preview(showBackground = true)
@Composable
fun test(){
    NonRunnableAlgorithms(question = "Has he?", answer = "true")
}