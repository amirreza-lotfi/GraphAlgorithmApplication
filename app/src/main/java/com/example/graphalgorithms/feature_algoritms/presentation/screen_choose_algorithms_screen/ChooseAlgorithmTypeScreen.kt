package com.example.graphalgorithms.feature_algoritms.presentation.screen_choose_algorithms_screen
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.graphalgorithms.feature_algoritms.presentation.RunAlgorithmsViewModel
import com.example.graphalgorithms.feature_algoritms.presentation.screen_choose_algorithms_screen.component.ItemOnChooseScreen
import com.example.graphalgorithms.feature_algoritms.presentation.screen_choose_algorithms_screen.component.TitleOfChooseScreen
import com.example.graphalgorithms.feature_node.presentation.ui.theme.orange

@Composable
fun ChooseAlgorithmTypeScreen(
    navController: NavController,
    viewModel: ChooseAlgorithmsTypeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    runAlgorithmsViewModel:RunAlgorithmsViewModel = hiltViewModel()
){
    Box(
        Modifier.fillMaxSize()
    ) {

        val options = viewModel.options

        Column(
            Modifier.padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp
            )
        ) {
            TitleOfChooseScreen()

            LazyColumn {
                items(options.size) { index ->
                    ItemOnChooseScreen(
                        option = options[index],
                        onItemClicked = {
                            viewModel.onItemSelectedEvent(index)
                        }
                    )
                }
            }
        }
        Button(
            onClick = {
                val route = viewModel.getSelectedOption().route
                navController.navigate(route)
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