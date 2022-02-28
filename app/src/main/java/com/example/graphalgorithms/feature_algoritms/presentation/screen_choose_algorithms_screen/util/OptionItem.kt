package com.example.graphalgorithms.feature_algoritms.presentation.screen_choose_algorithms_screen.util

data class OptionItem(
    val route:String,
    val title:String,
    val content:String = "",
    var isItemSelected:Boolean = false,
)
