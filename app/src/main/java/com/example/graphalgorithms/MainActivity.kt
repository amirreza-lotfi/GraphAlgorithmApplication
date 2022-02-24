package com.example.graphalgorithms

import DFSTraversalScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.BasicAlgorithmsScreen
import com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.screen_bfs_traversal.BFSTraversalScreen
import com.example.graphalgorithms.feature_algoritms.presentation.screen_choose_algorithms_screen.ChooseAlgorithmTypeScreen
import com.example.graphalgorithms.feature_node.presentation.NodeFeatureViewModel
import com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.AddEditNodeScreen
import com.example.graphalgorithms.feature_node.presentation.screen_graph.DrawingGraphScreen
import com.example.graphalgorithms.feature_node.presentation.screen_landing.LandingScreen
import com.example.graphalgorithms.feature_node.presentation.ui.theme.GraphAlgorithmsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @ExperimentalComposeUiApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var showLandingScreen by remember{ mutableStateOf(true)}
            if(showLandingScreen){
                LandingScreen {
                    showLandingScreen = false
                }
            }
            else {
                GraphAlgorithmsTheme {
                    val navController = rememberNavController()
                    val nodeFeatureViewModel: NodeFeatureViewModel = hiltViewModel()

                    NavHost(
                        navController = navController,
                        startDestination = GRAPH_SCREEN_ROUT
                    ) {
                        composable(route = GRAPH_SCREEN_ROUT) {
                            DrawingGraphScreen(navController, nodeFeatureViewModel)
                        }
                        composable(route = ADD_NODE_SCREEN_ROUT) {
                            AddEditNodeScreen(navController = navController, nodeFeatureViewModel)
                        }
                        composable(route = CHOOSE_ALGORITHMS_SCREEN_ROUT) {
                            ChooseAlgorithmTypeScreen(navController)
                        }
                        composable(route = BASIC_ALGORITHMS_SCREEN_ROUT) {
                            BasicAlgorithmsScreen(navController = navController)
                        }
                        composable(route = DFS_TRAVERSAL_SCREEN_ROUT) {
                            DFSTraversalScreen(navController)
                        }
                        composable(route = BFS_TRAVERSAL_SCREEN_ROUT) {
                            BFSTraversalScreen(navController)
                        }
                    }
                }
            }
        }
    }

    companion object{
        const val GRAPH_SCREEN_ROUT = "GraphScreen"
        const val ADD_NODE_SCREEN_ROUT = "AddNodeScreen"
        const val CHOOSE_ALGORITHMS_SCREEN_ROUT = "ChooseAlgorithmsScreen"
        const val BASIC_ALGORITHMS_SCREEN_ROUT = "BasicAlgorithmsScreen"
        const val DFS_TRAVERSAL_SCREEN_ROUT = "DFSTraversalScreen"
        const val BFS_TRAVERSAL_SCREEN_ROUT = "BFSTraversalScreen"

    }
}