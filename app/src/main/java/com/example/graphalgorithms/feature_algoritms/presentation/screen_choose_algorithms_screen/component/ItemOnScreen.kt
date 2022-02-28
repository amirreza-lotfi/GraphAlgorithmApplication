package com.example.graphalgorithms.feature_algoritms.presentation.screen_choose_algorithms_screen.component
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.graphalgorithms.feature_algoritms.presentation.screen_choose_algorithms_screen.util.OptionItem
import com.example.graphalgorithms.feature_node.presentation.ui.theme.black
import com.example.graphalgorithms.feature_node.presentation.ui.theme.lightGray
import com.example.graphalgorithms.feature_node.presentation.ui.theme.orange


@Composable
fun ItemOnScreen(
    option:OptionItem,
    onItemClicked:()->Unit
){
    val isItemSelected = option.isItemSelected

    val rowModifier = Modifier
        .padding(top = 8.dp)
        .fillMaxWidth()
        .clip(RoundedCornerShape(16.dp))
        .clickable {
            onItemClicked()
        }

    val modifierSelectedItem = rowModifier
        .background(Color.White)
        .border(1.5.dp,orange ,RoundedCornerShape(16.dp))
        .padding(12.dp)
        .padding(start = 12.dp)
    val modifierUnSelectedItem = rowModifier
        .background(lightGray)
        .padding(12.dp)
        .padding(start = 12.dp)

    Row(
        modifier = if(isItemSelected) modifierSelectedItem else modifierUnSelectedItem
    ) {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                option.title,
                style = MaterialTheme.typography.h5,
                color = black,
            )
            Spacer(Modifier.height(4.dp))

            if(option.content!= "") {
                Text(
                    option.content,
                    style = MaterialTheme.typography.body1,
                    color = Color.Gray
                )
            }
        }

    }
}