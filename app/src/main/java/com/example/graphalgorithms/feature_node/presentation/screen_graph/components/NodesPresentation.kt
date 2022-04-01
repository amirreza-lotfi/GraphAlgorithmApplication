package com.example.graphalgorithms.feature_node.presentation.screen_graph.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.graphalgorithms.feature_node.domain.entitiy.Node
import com.example.graphalgorithms.feature_node.presentation.GraphScreenViewModel
import com.example.graphalgorithms.feature_node.presentation.screen_graph.util.ScreenGraphEvent
import com.example.graphalgorithms.feature_node.presentation.ui.theme.teal
import kotlin.math.roundToInt


@Composable
fun NodesPresentation(
    screenViewModel: GraphScreenViewModel
) {
    key(screenViewModel.nodeList.size) {
        val nodeList = screenViewModel.nodeList
        for (node: Node in nodeList) {
            DrawNode(node, screenViewModel)
        }
    }
}

@Composable
fun DrawNode(
    node: Node,
    screenViewModel: GraphScreenViewModel
){
    var offsetXNode by remember { mutableStateOf(node.xNodePosition) }
    var offsetYNode by remember { mutableStateOf(node.yNodePosition) }



    val colorOfNode = if(node.isNodeSelected) Color.Red else teal

    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    Box{
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(44.dp)
                .offset {
                    IntOffset(
                        offsetXNode.roundToInt(),
                        offsetYNode.roundToInt()
                    )
                }
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragStart = {
                            screenViewModel.onScreenGraphEvent(
                                ScreenGraphEvent.SetRunAlgorithmButtonVisibility(
                                    false
                                )
                            )
                        },
                        onDragEnd = {
                            screenViewModel.onScreenGraphEvent(
                                ScreenGraphEvent.SetRunAlgorithmButtonVisibility(
                                    true
                                )
                            )
                        }
                    ) { change, dragAmount ->
                        change.consumeAllChanges()
                        offsetXNode += dragAmount.x
                        offsetYNode += dragAmount.y
                        if (offsetXNode.toDp() < 30f.toDp())
                            offsetXNode = 30f
                        else if (offsetXNode.toDp() + 90.dp > screenWidth)
                            offsetXNode = (screenWidth - 90.dp).toPx()

                        if (offsetYNode.toDp() < 30f.toDp())
                            offsetYNode = 30f
                        else if (offsetYNode.toDp() + 235.dp > screenHeight)
                            offsetYNode = (screenHeight - 235.dp).toPx()


                        screenViewModel.onScreenGraphEvent(
                            ScreenGraphEvent.NodePositionChanged(
                                node,
                                offsetXNode,
                                offsetYNode
                            )
                        )
                    }
                }
                .background(colorOfNode, shape = CircleShape)
                .layout() { measurable, constraints ->
                    // Measure the composable
                    val placeable = measurable.measure(constraints)

                    //get the current max dimension to assign width=height
                    val currentHeight = placeable.height
                    var heightCircle = currentHeight
                    if (placeable.width > heightCircle)
                        heightCircle = placeable.width

                    //assign the dimension and the center position
                    layout(heightCircle, heightCircle) {
                        // Where the composable gets placed
                        placeable.placeRelative(0, (heightCircle - currentHeight) / 2)
                    }
                }
                .clickable(
                    indication = null,
                    interactionSource = MutableInteractionSource()
                ) {
                    screenViewModel.onScreenGraphEvent(ScreenGraphEvent.OnNodeClicked(node))
                }
        ) {
            Text(
                text = node.label,
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = Modifier
                    .padding(4.dp)
                    .defaultMinSize(24.dp) //Use a min size for short text.
            )
        }
    }

}