import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.screen_bfs_traversal.component.*
import com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.screen_dfs_traversal.DfsViewModel
import com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.screen_dfs_traversal.util.DfsUiEvent
import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import com.example.graphalgorithms.feature_node.domain.entitiy.Node
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun DFSTraversalScreen(
    navController: NavController,
    startingNode:String,
    dfsViewModel: DfsViewModel = DfsViewModel(
        hiltViewModel(
            navController.getBackStackEntry("BasicAlgorithmsScreen")
        )
    )
) {
    dfsViewModel.starterNodeForDfsAlgorithms = startingNode

    val coroutineScope = rememberCoroutineScope()
    val visitedNodes = remember{ mutableStateListOf<Node>()}
    val visitedEdges = remember{ mutableStateListOf<Edge>()}

    var visitedNodeText: StringBuffer

    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        var isPlayButtonVisible by rememberSaveable {
            mutableStateOf(true)
        }

        DrawGraphEdges(dfsViewModel.edgeList)
        DrawGraphNodes(dfsViewModel.nodeList)

        DrawVisitedEdgeWithRedColor(visitedEdges)
        DrawVisitedNodeWithRedColor(visitedNodes)

        PlayAlgorithmsButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .width(160.dp),
            isPlayButtonVisible = isPlayButtonVisible,
            onClick = {
                visitedNodeText = StringBuffer("")
                visitedNodes.clear()
                visitedEdges.clear()
                dfsViewModel.onPlayButtonClicked()
                isPlayButtonVisible = false
            }
        )
        
        visitedNodeText = dfsViewModel.getVisitedNodeTextForScreen(visitedNodes)
        
        Column(
            Modifier
                .fillMaxWidth()
                .height(160.dp)
                .padding(bottom = 64.dp, start = 24.dp, end = 24.dp)
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = visitedNodeText.toString(), fontSize = 24.sp
            )
        }

        coroutineScope.launch {
            dfsViewModel.dfsUiEvent.collect { event ->
                when (event) {
                    is DfsUiEvent.OnAlgorithmEnds -> {
                        isPlayButtonVisible = true
                    }
                    is DfsUiEvent.DrawNodeOnScreen->{
                        visitedNodes.add(event.node)
                    }
                    is DfsUiEvent.DrawEdgeOnScreen->{
                        visitedEdges.add(event.edge)
                    }
                }
            }
        }
    }
}




