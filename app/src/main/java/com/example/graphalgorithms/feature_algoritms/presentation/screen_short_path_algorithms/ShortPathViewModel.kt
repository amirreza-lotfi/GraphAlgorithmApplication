package com.example.graphalgorithms.feature_algoritms.presentation.screen_short_path_algorithms

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.graphalgorithms.MainActivity
import com.example.graphalgorithms.MainActivity.Companion.BELLMAN_FORD_ALGORITHM_SCREEN_ROUT
import com.example.graphalgorithms.MainActivity.Companion.DIAL_ALGORITHM_SCREEN_ROUT
import com.example.graphalgorithms.MainActivity.Companion.DIJKSTRA_ALGORITHM_SCREEN_ROUT
import com.example.graphalgorithms.MainActivity.Companion.FLOYD_WARSHALL_ALGORITHM_SCREEN_ROUT
import com.example.graphalgorithms.MainActivity.Companion.JOHNSON_ALGORITHM_SCREEN_ROUT
import com.example.graphalgorithms.feature_algoritms.presentation.screen_choose_algorithms_screen.util.OptionItem


class ShortPathViewModel:ViewModel() {
    private val _selectedItem = mutableStateOf(0)

    val options = mutableStateListOf(
        OptionItem(
            route = DIJKSTRA_ALGORITHM_SCREEN_ROUT,
            title = "Dijkstra Algorithm",
            isItemSelected = true
        ),

        OptionItem(
            route = DIAL_ALGORITHM_SCREEN_ROUT,
            title= "Dial Algorithm"
        ),

        OptionItem(
            route = JOHNSON_ALGORITHM_SCREEN_ROUT,
            title = "Johnson Algorithm",
        ),

        OptionItem(
            route = FLOYD_WARSHALL_ALGORITHM_SCREEN_ROUT,
            title = "Floyd-Warshall Algorithm",
        ),

        OptionItem(
            BELLMAN_FORD_ALGORITHM_SCREEN_ROUT,
            title = "Bellman-Ford Algorithm"
        )
    )

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