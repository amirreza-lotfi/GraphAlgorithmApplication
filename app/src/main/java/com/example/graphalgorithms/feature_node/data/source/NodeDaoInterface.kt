package com.example.graphalgorithms.feature_node.data.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.graphalgorithms.feature_node.domain.entitiy.Node
import kotlinx.coroutines.flow.Flow

@Dao
interface NodeDaoInterface {

    @Insert(onConflict = REPLACE)
    suspend fun add(node: Node):Long

    @Delete
    suspend fun delete(node: Node):Int

    @Query("select * from node_table order by label ASC")
    suspend fun getNodesFromDatabase(): List<Node>

    @Query("select * from node_table where label==:labelInput")
    suspend fun getNodeFromDatabase(labelInput:String):Node

    @Query("delete from node_table")
    suspend fun deleteAllNodes()
}