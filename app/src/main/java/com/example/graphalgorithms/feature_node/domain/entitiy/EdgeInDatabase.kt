package com.example.graphalgorithms.feature_node.domain.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.graphalgorithms.feature_node.presentation.NodeFeatureViewModel.Companion.findNodeByLabel
import com.example.graphalgorithms.feature_node.presentation.NodeFeatureViewModel.Companion.randomEdgeId

@Entity(tableName = "edge_table")
data class EdgeInDatabase(
    val fromLabel:String,
    val toLabel: String,
    var weight:Float = 0f,
    @PrimaryKey
    var edgeId:Int = randomEdgeId()
){
    companion object{
        fun getEdge(edgeInDatabase:EdgeInDatabase, nodeList: List<Node>):Edge{
            return Edge(
                findNodeByLabel(edgeInDatabase.fromLabel,nodeList),
                findNodeByLabel(edgeInDatabase.toLabel,nodeList),
                edgeInDatabase.weight,
                edgeInDatabase.edgeId,
            )
        }
    }
}
