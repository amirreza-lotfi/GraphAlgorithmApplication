package com.example.graphalgorithms.feature_algoritms.presentation.screen_short_path_algorithms.screen_dijkstra.util

data class AdjacentOfNode(
    val label:String,
    val distance:Float,
    val visited:Boolean = false
)