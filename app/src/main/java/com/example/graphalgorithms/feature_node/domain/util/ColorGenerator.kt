package com.example.graphalgorithms.feature_node.domain.util

import androidx.compose.ui.graphics.Color
import java.util.*
import kotlin.random.Random
import kotlin.random.Random.Default.nextInt

class ColorGenerator {
    init{
        generateUniqueColors()
    }
    companion object{
        val colors = mutableListOf<Color>()
        fun generateUniqueColors(){
            while(colors.size!=100){
                val generatedColor = Color(nextInt() %256, nextInt() %256, nextInt() %256)
                colors.add(generatedColor)
            }
        }
    }
}