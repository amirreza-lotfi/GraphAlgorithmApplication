package com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.components_of_basic_algorithms.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.graphalgorithms.feature_node.presentation.ui.theme.lightGray

@Composable
fun NonRunnableAlgorithms(
    question:String,
    answer:String
){
    Row(
        Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(lightGray)
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center
    ){
        Text(text = question,Modifier.align(Alignment.CenterVertically),style = MaterialTheme.typography.body1)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = answer,Modifier.align(Alignment.CenterVertically),style = MaterialTheme.typography.body1)
    }
}
