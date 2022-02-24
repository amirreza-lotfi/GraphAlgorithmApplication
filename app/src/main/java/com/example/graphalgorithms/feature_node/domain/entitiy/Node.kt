package com.example.graphalgorithms.feature_node.domain.entitiy

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "node_table")
data class Node(@PrimaryKey var label:String) {

    var xNodePosition = 0f
    var yNodePosition = 0f
    @Ignore
    var isNodeSelected = false

    @Ignore
    var edges = mutableListOf<Edge>()

    fun setPosition(xNodePosition:Float, yNodePosition:Float){
        this.xNodePosition = xNodePosition
        this.yNodePosition = yNodePosition
    }
    fun copyData(node:Node){
        this.edges = node.edges
        this.isNodeSelected = node.isNodeSelected
        this.label = node.label
        this.xNodePosition = node.xNodePosition
        this.yNodePosition = node.yNodePosition
    }
}
