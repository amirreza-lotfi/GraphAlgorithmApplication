package com.example.graphalgorithms.feature_node.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.graphalgorithms.feature_node.domain.entitiy.EdgeWithLabels
import com.example.graphalgorithms.feature_node.domain.entitiy.Node

@Database(entities = [Node::class, EdgeWithLabels::class], version = 1)
abstract class GraphDatabase:RoomDatabase() {
    abstract val nodeDou: NodeDaoInterface
    abstract val edgeDou: EdgeDaoRepository
}