package com.example.graphalgorithms.feature_node.data.source

import androidx.room.*
import com.example.graphalgorithms.feature_node.domain.entitiy.EdgeInDatabase

@Dao
interface EdgeDaoRepository {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(edge: EdgeInDatabase):Long

    @Delete
    suspend fun delete(edge: EdgeInDatabase):Int

    @Query("select * from edge_table")
    suspend fun getEdges():List<EdgeInDatabase>
}