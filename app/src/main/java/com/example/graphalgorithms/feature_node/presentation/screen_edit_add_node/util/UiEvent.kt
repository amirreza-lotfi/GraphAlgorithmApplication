package com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.util

sealed class UiEvent{
    data class ShowErrorSnackbar(val str:String):UiEvent()
    object SaveNode:UiEvent()
}
