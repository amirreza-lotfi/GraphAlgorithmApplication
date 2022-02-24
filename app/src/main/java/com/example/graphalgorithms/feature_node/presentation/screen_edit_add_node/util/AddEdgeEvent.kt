package com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.util

import com.example.graphalgorithms.feature_node.domain.entitiy.Edge

sealed class AddEdgeEvent {
    data class OnWeightChanged(val weight: String): AddEdgeEvent()
    data class OnDeleteEdgeClicked(val edge: Edge) : AddEdgeEvent()
}