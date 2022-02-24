package com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.util

sealed class AddEditNodeScreenEvent{
    data class OnSaveEditNodeButtonClicked(val label: String) :AddEditNodeScreenEvent()
    object OnCancelEditNodeButtonClicked:AddEditNodeScreenEvent()
    data class SaveEdgeButtonClicked(val toNodeLabel:String):AddEditNodeScreenEvent()
}
