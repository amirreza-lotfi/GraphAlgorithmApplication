package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_basic_algorithms.components_of_basic_algorithms.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_basic_algorithms.BasicAlgorithmsViewModel
import com.example.graphalgorithms.feature_node.presentation.ScreenGraphViewModel
import com.example.graphalgorithms.feature_node.presentation.ui.theme.lightGray

@Composable
fun RunnableWithStarterNodeAlgorithms(
    viewModel: BasicAlgorithmsViewModel,
    algorithmsName:String,
    onClickItem:()->Unit
) {
    val nodesLabel = ScreenGraphViewModel.getNodeLabels()
    val selectedNodeIndex = remember{mutableStateOf(0)}
    val defaultStartNodeLabel = remember {
        mutableStateOf(nodesLabel[selectedNodeIndex.value])
    }
    
    Row(
        Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(lightGray)
            .padding(vertical = 8.dp, horizontal = 24.dp),
        horizontalArrangement = Arrangement.Center
    ){
        Text(text = algorithmsName,Modifier.align(Alignment.CenterVertically),style = MaterialTheme.typography.body1)

        Spacer(Modifier.width(12.dp))

        NodesDropDown(nodesLabel,selectedNodeIndex, defaultStartNodeLabel )
        Box(
            Modifier.fillMaxSize()
        ){
            Button(
                onClick = {
                    viewModel.starterNodeForBfsAlgorithms = defaultStartNodeLabel.value
                    onClickItem() 
                },
                modifier = Modifier.align(Alignment.CenterEnd),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)

            ) {
                Text("Run", color = Color.White)
                Spacer(Modifier.width(4.dp))
            }
        }
    }
}