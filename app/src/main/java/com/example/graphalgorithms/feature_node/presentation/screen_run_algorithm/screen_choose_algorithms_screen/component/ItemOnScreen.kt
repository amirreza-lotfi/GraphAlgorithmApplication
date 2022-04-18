package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_choose_algorithms_screen.component
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.graphalgorithms.MainActivity
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_choose_algorithms_screen.util.OptionItem
import com.example.graphalgorithms.feature_node.presentation.ui.theme.*


@Composable
fun ItemOnScreen(
    option: OptionItem,
    onItemClicked:()->Unit
){
    val isItemSelected = option.isItemSelected

    val rowModifier = Modifier
        .padding(top = 8.dp)
        .fillMaxWidth()
        .clip(RoundedCornerShape(16.dp))
        .clickable(
            indication = rememberRipple(color = teal),
            interactionSource =  remember { MutableInteractionSource() }
        ){
            onItemClicked()
        }

    val modifierSelectedItem = rowModifier
        .background(white)
        .border(1.5.dp, teal, RoundedCornerShape(16.dp))
        .padding(16.dp)
        .padding(start = 12.dp)
    val modifierUnSelectedItem = rowModifier
        .background(lightGray)
        .padding(16.dp)
        .padding(start = 12.dp)

    Row(
        modifier = if(isItemSelected) modifierSelectedItem else modifierUnSelectedItem,
    ) {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                option.title,
                color = black,
                fontSize = 28.sp
            )
            Spacer(Modifier.height(4.dp))

        }

    }
}
