package com.example.graphalgorithms

import DFSTraversalScreen
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.UndirectedGraphProvider
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_basic_algorithms.BasicAlgorithmsScreen
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_basic_algorithms.BasicAlgorithmsViewModel
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_bfs_traversal.BFSTraversalScreen
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_choose_algorithms_screen.ChooseAlgorithmTypeScreen
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_choose_algorithms_screen.ChooseAlgorithmsTypeViewModel
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_choose_starting_node.ChooseStartingNodeScreen
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_dijkstra.DijkstraAlgorithmScreen
import com.example.graphalgorithms.feature_node.presentation.GraphScreenViewModel
import com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.AddEditNodeScreen
import com.example.graphalgorithms.feature_node.presentation.screen_graph.GraphScreen

import com.example.graphalgorithms.feature_node.presentation.screen_landing.LandingScreen
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_kruskal.KruskalScreen
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_prim.PrimScreen
import com.example.graphalgorithms.feature_node.presentation.ui.theme.GraphAlgorithmsTheme
import com.example.graphalgorithms.feature_node.presentation.ui.theme.LandingPageTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var graphScreenViewModel: GraphScreenViewModel
    @SuppressLint("UnrememberedGetBackStackEntry")
    @ExperimentalComposeUiApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
                var showLandingScreen by rememberSaveable{ mutableStateOf(true)}

                graphScreenViewModel = hiltViewModel()

                if(showLandingScreen){
                    LandingPageTheme {
                        LandingScreen {
                            showLandingScreen = false
                        }
                    }
                }
                else {
                    GraphAlgorithmsTheme {

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = GRAPH_SCREEN_ROUT
                    ) {

                        composable(route = GRAPH_SCREEN_ROUT) {
                            GraphScreen(graphScreenViewModel,
                                onNavigateToAddEditScreen = {
                                    navController.navigate(ADD_NODE_SCREEN_ROUT)
                                },
                                onNavigateToChooseAlgorithmScreen = {
                                    navController.navigate(CHOOSE_ALGORITHMS_SCREEN_ROUT)
                                }
                            )
                        }

                        composable(route = ADD_NODE_SCREEN_ROUT) {
                            AddEditNodeScreen(navController = navController, graphScreenViewModel)
                        }

                        composable(route = CHOOSE_ALGORITHMS_SCREEN_ROUT) {
                            val viewModel:ChooseAlgorithmsTypeViewModel = viewModel()
                            val undirectedGraphProvider: UndirectedGraphProvider = hiltViewModel()

                            ChooseAlgorithmTypeScreen(
                                viewModel,
                                onNavigateToAlgorithmsScreen = { option->
                                    if(option.hasStartingNode){
                                        navController.navigate("$CHOOSE_STARTING_NODE_SCREEN_ROUT/${option.route}")
                                    }else{
                                        navController.navigate(option.route)
                                    }
                                },

                                onBackArrowClicked = {
                                    navController.popBackStack()
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

                            BasicAlgorithmsScreen(
                                viewModel,
                                onBackArrowClicked = {
                                    navController.popBackStack()
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


                        composable(
                            route = "$CHOOSE_STARTING_NODE_SCREEN_ROUT/{destinationRout}",
                            arguments = listOf(
                                navArgument("destinationRout"){
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            val destinationRout = it.arguments?.getString("destinationRout")!!

                            ChooseStartingNodeScreen(
                                navController = navController
                            ){ startingNode ->
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

                        composable(route = KRUSKAL_ALGORITHM_SCREEN_ROUT){
                            KruskalScreen(navController)
                        }

                        composable(route = PRIM_ALGORITHM_SCREEN_ROUT){
                            PrimScreen(navController)
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
        const val PRIM_ALGORITHM_SCREEN_ROUT = "PrimAlgorithmScreen"
        const val KRUSKAL_ALGORITHM_SCREEN_ROUT = "KruskalAlgorithmScreen"
        const val BORUVKA_ALGORITHM_SCREEN_ROUT = "BoruvkaAlgorithmScreen"
        const val MST_ALGORITHM_SCREEN_ROUT = "MstAlgorithmScreen"
    }

}