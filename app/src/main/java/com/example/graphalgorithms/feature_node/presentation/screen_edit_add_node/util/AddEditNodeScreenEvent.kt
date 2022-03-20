package com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.util

import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import com.example.graphalgorithms.feature_node.domain.entitiy.EdgeWithLabels

sealed class AddEditNodeScreenEvent{
    object OnSaveNodeButtonClicked :AddEditNodeScreenEvent()
    object OnCancelEditNodeButtonClicked:AddEditNodeScreenEvent()
    object OnAddEdgeRowSelection:AddEditNodeScreenEvent()
    data class SaveEdgeButtonClicked(val toNodeLabel:String):AddEditNodeScreenEvent()
    data class OnWeightChanged(val weight: String): AddEditNodeScreenEvent()
    data class OnNodeLabelChanged(val nodeLabel:String): AddEditNodeScreenEvent()
    data class OnDeleteEdgeClicked(val edge: EdgeWithLabels) : AddEditNodeScreenEvent()
}
