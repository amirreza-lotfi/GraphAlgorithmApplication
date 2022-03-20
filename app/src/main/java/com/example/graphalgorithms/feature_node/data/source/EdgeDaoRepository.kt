package com.example.graphalgorithms.feature_node.data.source

import androidx.room.*
import com.example.graphalgorithms.feature_node.domain.entitiy.EdgeWithLabels

@Dao
interface EdgeDaoRepository {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(edge: EdgeWithLabels):Long

    @Delete
    suspend fun delete(edge: EdgeWithLabels):Int

    @Query("select * from edge_table order by fromLabel ASC")
    suspend fun getEdges():List<EdgeWithLabels>

    @Query("delete from edge_table")
    suspend fun deleteAllEdges()
}