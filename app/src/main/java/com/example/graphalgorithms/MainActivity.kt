package com.example.graphalgorithms

import DFSTraversalScreen
import android.annotation.SuppressLint
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
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.graphalgorithms.feature_algoritms.presentation.UndirectedGraphProvider
import com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.BasicAlgorithmsScreen
import com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.BasicAlgorithmsViewModel
import com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.screen_bfs_traversal.BFSTraversalScreen
import com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.screen_bfs_traversal.BFSTraversalViewModel
import com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.screen_dfs_traversal.DfsViewModel
import com.example.graphalgorithms.feature_algoritms.presentation.screen_choose_algorithms_screen.ChooseAlgorithmTypeScreen
import com.example.graphalgorithms.feature_algoritms.presentation.screen_choose_algorithms_screen.ChooseAlgorithmsTypeViewModel
import com.example.graphalgorithms.feature_algoritms.presentation.screen_short_path_algorithms.ShortPathScreen
import com.example.graphalgorithms.feature_algoritms.presentation.screen_short_path_algorithms.ShortPathViewModel
import com.example.graphalgorithms.feature_algoritms.presentation.screen_short_path_algorithms.screen_choose_starting_node.ChooseStartingNodeScreen
import com.example.graphalgorithms.feature_algoritms.presentation.screen_short_path_algorithms.screen_dijkstra.DijkstraAlgorithmScreen
import com.example.graphalgorithms.feature_node.presentation.NodeFeatureViewModel
import com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.AddEditNodeScreen
import com.example.graphalgorithms.feature_node.presentation.screen_graph.GraphScreen

import com.example.graphalgorithms.feature_node.presentation.screen_landing.LandingScreen
import com.example.graphalgorithms.feature_node.presentation.ui.theme.GraphAlgorithmsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @SuppressLint("UnrememberedGetBackStackEntry")
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
                            GraphScreen(nodeFeatureViewModel,
                                onNavigateToAddEditScreen = {
                                    navController.navigate(ADD_NODE_SCREEN_ROUT)
                                },
                                onNavigateToChooseAlgorithmScreen = {
                                    navController.navigate(CHOOSE_ALGORITHMS_SCREEN_ROUT)
                                }
                            )
                        }

                        composable(route = ADD_NODE_SCREEN_ROUT) {
                            AddEditNodeScreen(navController = navController, nodeFeatureViewModel)
                        }

                        composable(route = CHOOSE_ALGORITHMS_SCREEN_ROUT) {
                            val viewModel = ChooseAlgorithmsTypeViewModel()
                            val undirectedGraphProvider:UndirectedGraphProvider = hiltViewModel()

                            ChooseAlgorithmTypeScreen(viewModel,
                                onNavigateToAlgorithmsScreen = { route->
                                    navController.navigate(route)
                                }
                            )
                        }

                        composable(
                            route = BASIC_ALGORITHMS_SCREEN_ROUT,
                        ) {
                            val viewModel = BasicAlgorithmsViewModel(
                                hiltViewModel(
                                    navController.getBackStackEntry(CHOOSE_ALGORITHMS_SCREEN_ROUT)
                                )
                            )

                            BasicAlgorithmsScreen(viewModel,
                                onNavigateToBFSScreen = { startingNode ->
                                    navController.navigate("$BFS_TRAVERSAL_SCREEN_ROUT/$startingNode")
                                },
                                onNavigateToDFSScreen = { startingNode ->
                                    navController.navigate("$DFS_TRAVERSAL_SCREEN_ROUT/$startingNode")
                                }
                            )
                        }

                        composable(
                            route = "$DFS_TRAVERSAL_SCREEN_ROUT/{startingNode}",
                            arguments = listOf(
                                navArgument("startingNode"){
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            val startingNode = it.arguments?.get("startingNode").toString()
                            DFSTraversalScreen(navController = navController, startingNode)
                        }

                        composable(
                            route = "$BFS_TRAVERSAL_SCREEN_ROUT/{startingNode}",
                            arguments = listOf(
                                navArgument("startingNode"){
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            val startingNode = it.arguments?.get("startingNode").toString()
                            BFSTraversalScreen(navController,startingNode)
                        }

                        composable(route = SHORT_PATH_SCREEN_ROUT){
                            val viewModel = ShortPathViewModel()
                            ShortPathScreen(
                                viewModel = viewModel,
                                onNavigateToScreen = { route ->
                                    navController.navigate(route)
                                }
                            )
                        }

                        composable(
                            route = "$CHOOSE_STARTING_NODE_SCREEN_ROUT/{destinationRout}",
                            arguments = listOf(
                                navArgument("destinationRout"){
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            val destinationRout = it.arguments?.getString("destinationRout")!!

                            ChooseStartingNodeScreen{ startingNode ->
                                val rout = "$destinationRout/$startingNode"
                                navController.navigate(rout)
                            }
                        }

                        composable(
                            route = "$DIJKSTRA_ALGORITHM_SCREEN_ROUT/{startingNode}",
                            arguments = listOf(
                                navArgument("startingNode"){
                                    type = NavType.StringType
                                }
                            )
                        ){
                            val startNode = it.arguments?.getString("startingNode").toString()
                            DijkstraAlgorithmScreen(navController = navController, startingNode = startNode)
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
        const val SHORT_PATH_SCREEN_ROUT = "ShortPathScreen"
        const val DIJKSTRA_ALGORITHM_SCREEN_ROUT = "DijkstraScreen"
        const val BELLMAN_FORD_ALGORITHM_SCREEN_ROUT = "BellmanFordAlgorithm"
        const val FLOYD_WARSHALL_ALGORITHM_SCREEN_ROUT = "FloydWarshallAlgorithm"
        const val JOHNSON_ALGORITHM_SCREEN_ROUT = "JohnsonAlgorithm"
        const val DIAL_ALGORITHM_SCREEN_ROUT = "DialAlgorithm"
        const val CHOOSE_STARTING_NODE_SCREEN_ROUT = "ChooseStartingNodeScreen"
    }
}