package com.example.graphalgorithms.feature_node.domain.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.graphalgorithms.feature_node.presentation.NodeFeatureViewModel.Companion.findNodeByLabel
import com.example.graphalgorithms.feature_node.presentation.NodeFeatureViewModel.Companion.randomEdgeId

@Entity(tableName = "edge_table")
data class EdgeWithLabels(
    val fromLabel:String,
    val toLabel: String,
    var weight:Float = 0f,
    @PrimaryKey
    var edgeId:Int = randomEdgeId()
){
    companion object{
        fun getEdge(edgeWithLabels:EdgeWithLabels, nodeList: List<Node>):Edge{
            return Edge(
                findNodeByLabel(edgeWithLabels.fromLabel,nodeList),
                findNodeByLabel(edgeWithLabels.toLabel,nodeList),
                edgeWithLabels.weight,
                edgeWithLabels.edgeId,
            )
        }
    }
}
