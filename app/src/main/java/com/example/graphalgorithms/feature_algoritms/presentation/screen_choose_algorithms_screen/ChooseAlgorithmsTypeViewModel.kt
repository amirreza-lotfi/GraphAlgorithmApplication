package com.example.graphalgorithms.feature_algoritms.presentation.screen_choose_algorithms_screen

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.graphalgorithms.MainActivity.Companion.BASIC_ALGORITHMS_SCREEN_ROUT
import com.example.graphalgorithms.feature_algoritms.presentation.screen_choose_algorithms_screen.util.OptionItem

class ChooseAlgorithmsTypeViewModel:ViewModel(){
    val options = mutableStateListOf(
        OptionItem(
           BASIC_ALGORITHMS_SCREEN_ROUT,
            "Basic Algorithms",
            "24 algorithms",
            true),

        OptionItem(
            "MinimumSpanningTreeScreen",
            "Minimum Spanning Tree",
            "24 algorithms"),

        OptionItem(
            "ShortPathAlgorithmsScreen",
            "Short Path Algorithms",
            "24 algorithms"),

        OptionItem(
            "BackTrackingAlgorithmsScreen",
            "BackTracking Algorithms",
            "24 algorithms"),

        OptionItem(
            "TopologicalAlgorithmsScreen",
            "Topological Algorithms",
            "24 algorithms"),
    )

    private val _selectedItem = mutableStateOf(0)


    fun getSelectedOption():OptionItem{
        return options[_selectedItem.value]
    }

    fun onItemSelectedEvent(index:Int){
        for (i:Int in 0 until options.size) {
            options[i] = options[i].copy(
                isItemSelected = false
            )
        }
        _selectedItem.value = index
        options[index].isItemSelected = true
        options[index] = options[index].copy(
            isItemSelected = true
        )
    }
}