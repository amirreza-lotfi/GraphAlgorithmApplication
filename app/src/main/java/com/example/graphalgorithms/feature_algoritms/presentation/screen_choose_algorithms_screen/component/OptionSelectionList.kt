package com.example.graphalgorithms.feature_algoritms.presentation.screen_choose_algorithms_screen.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.graphalgorithms.MainActivity
import com.example.graphalgorithms.MainActivity.Companion.CHOOSE_STARTING_NODE_SCREEN_ROUT
import com.example.graphalgorithms.feature_algoritms.presentation.screen_choose_algorithms_screen.util.OptionItem
import com.example.graphalgorithms.feature_node.presentation.ui.theme.orange


@Composable
fun OptionSelectionList(
    options:MutableList<OptionItem>,
    onItemSelectedEvent:(index:Int)-> Unit,
    rout:String,
    onNavigateToAlgorithmsScreen: (rout: String) -> Unit,
    paddingTop: Dp = 16.dp,
    paddingBottom: Dp = 0.dp
){
    Box(
        Modifier.fillMaxSize()
    ) {
        Column(
            Modifier.padding(
                top = paddingTop,
                start = 16.dp,
                end = 16.dp,
                bottom = paddingBottom
            )
        ) {
            TitleOfScreen()

            LazyColumn {
                items(options.size) { index ->
                    ItemOnScreen(
                        option = options[index],
                        onItemClicked = {
                            onItemSelectedEvent(index)
                        }
                    )
                }
            }
        }
        Button(
            onClick = {
                onNavigateToAlgorithmsScreen(rout)
            },
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.BottomCenter)
                .width(168.dp)
                .height(48.dp)
                .clip(RoundedCornerShape(8.dp)),
            colors = ButtonDefaults.buttonColors(backgroundColor = orange)
        ) {
            Text("Next", color = Color.White)
        }
    }
}