package com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.screen_bfs_traversal.component

import androidx.compose.runtime.*
import com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.screen_bfs_traversal.BFSTraversalViewModel
import com.example.graphalgorithms.feature_node.domain.entitiy.Node
import com.example.graphalgorithms.feature_node.presentation.ui.theme.teal

@Composable
fun DrawGraphNodes(
    nodeList:List<Node>
) {

    for(node: Node in nodeList){
        DrawSingleNode(node,teal)
    }
}

