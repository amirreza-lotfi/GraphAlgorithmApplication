package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_choose_algorithms_screen.util

data class OptionItem(
    val route:String,
    val title:String,
    var isItemSelected:Boolean = false,
    var hasStartingNode:Boolean = false
)
