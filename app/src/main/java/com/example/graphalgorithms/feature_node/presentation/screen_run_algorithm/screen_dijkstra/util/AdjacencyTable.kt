package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_dijkstra.util

data class AdjacencyTable(
    val visibilityArray:IntArray,
    val distances:FloatArray,
    val lastNodeArray: MutableList<String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AdjacencyTable

        if (!visibilityArray.contentEquals(other.visibilityArray)) return false
        if (!distances.contentEquals(other.distances)) return false
        if (lastNodeArray != other.lastNodeArray) return false

        return true
    }

    override fun hashCode(): Int {
        var result = visibilityArray.contentHashCode()
        result = 31 * result + distances.contentHashCode()
        result = 31 * result + lastNodeArray.hashCode()
        return result
    }
}