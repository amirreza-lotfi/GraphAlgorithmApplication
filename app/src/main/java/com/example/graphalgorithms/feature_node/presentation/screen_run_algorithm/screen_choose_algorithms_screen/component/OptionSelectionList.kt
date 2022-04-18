package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_choose_algorithms_screen.component

import androidx.compose.foundation.background
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
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_choose_algorithms_screen.ChooseAlgorithmsTypeViewModel
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_choose_algorithms_screen.util.OptionItem
import com.example.graphalgorithms.feature_node.presentation.ui.theme.teal
import com.example.graphalgorithms.feature_node.presentation.ui.theme.white


@Composable
fun OptionSelectionList(
    viewModel: ChooseAlgorithmsTypeViewModel,
    onNavigateToAlgorithmsScreen: (rout: OptionItem) -> Unit,
    onBackArrowClicked:()->Unit,
    paddingTop: Dp = 8.dp,
    paddingBottom: Dp = 0.dp
){
    Box(
        Modifier.fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(bottom = 16.dp)

    ) {
        Column(
            Modifier.padding(
                top = paddingTop,
                start = 16.dp,
                end = 16.dp,
                bottom = paddingBottom
            )
        ) {
            TitleOfScreen(
                onBackArrowClicked = {onBackArrowClicked()},
                text = "Choose One!!"
            )

            LazyColumn {
                items(viewModel.options.size) { index ->
                    ItemOnScreen(
                        option = viewModel.options[index],
                        onItemClicked = {
                            viewModel.onItemSelectedEvent(index)
                        }
                    )
                }
            }
        }
        Button(
            onClick = {
                onNavigateToAlgorithmsScreen(viewModel.getSelectedOption())
            },
            modifier = Modifier
                .padding(top = 16.dp)
                .align(Alignment.BottomCenter)
                .width(168.dp)
                .height(48.dp)
                .clip(RoundedCornerShape(8.dp)),
            colors = ButtonDefaults.buttonColors(backgroundColor = teal)
        ) {
            Text("Next", color = white)
        }
    }
}