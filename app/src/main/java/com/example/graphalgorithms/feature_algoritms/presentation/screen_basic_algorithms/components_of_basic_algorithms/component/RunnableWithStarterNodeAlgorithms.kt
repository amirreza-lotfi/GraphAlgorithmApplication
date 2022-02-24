package com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.components_of_basic_algorithms.component

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.PlaylistPlay
import androidx.compose.material.icons.filled.RunCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.BasicAlgorithmsViewModel
import com.example.graphalgorithms.feature_node.presentation.NodeFeatureViewModel
import com.example.graphalgorithms.feature_node.presentation.ui.theme.lightGray
import com.example.graphalgorithms.feature_node.presentation.ui.theme.orange

@Composable
fun RunnableWithStarterNodeAlgorithms(
    viewModel: BasicAlgorithmsViewModel,
    algorithmsName:String,
    onClickItem:()->Unit
) {
    val nodesLabel = NodeFeatureViewModel.getNodeLabels()
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
            .padding(12.dp),
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