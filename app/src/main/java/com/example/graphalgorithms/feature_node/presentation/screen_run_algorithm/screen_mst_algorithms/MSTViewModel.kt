package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_mst_algorithms

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.graphalgorithms.MainActivity
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_choose_algorithms_screen.util.OptionItem

class MSTViewModel:ViewModel() {
    private val _selectedItem = mutableStateOf(0)

    val options = mutableStateListOf(
        OptionItem(
            route = MainActivity.PRIM_ALGORITHM_SCREEN_ROUT,
            title = "Prim Algorithm",
            isItemSelected = true
        ),

        OptionItem(
            route = MainActivity.KRUSKAL_ALGORITHM_SCREEN_ROUT,
            title= "Kruskal Algorithm"
        ),

        OptionItem(
            route = MainActivity.BORUVKA_ALGORITHM_SCREEN_ROUT,
            title = "Boruvka Algorithm",
        ),
    )

    fun getSelectedOption(): OptionItem {
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