package com.example.graphalgorithms.feature_node.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.example.graphalgorithms.feature_node.domain.entitiy.Node
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@SmallTest
class NodeDaoInterfaceTest {

    private lateinit var database:GraphDatabase
    private lateinit var dao:NodeDaoInterface

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUpDatabase(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            GraphDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.nodeDou
    }

    @Test
    fun addNodeTest() = runBlockingTest{
        val a = Node("a")
        dao.add(a)
        val aNode = dao.getNodeFromDatabase(a.uniqueId)
//        assert(a == aNode)
//        assertThat(a == aNode).isTrue()
        assertThat(aNode).isEqualTo(a)
    }

    @Test
    fun deleteNodeTest() = runBlockingTest {
        val a = Node("a")
        dao.add(a)
        dao.delete(a)
        val allNodes = dao.getNodesFromDatabase()
//        assert(!allNodes.contains(a))
        assertThat(allNodes).doesNotContain(a)
    }

    @Test
    fun replaceNodeTest() = runBlockingTest {
        val node = Node("A")
        val defaultYValue = node.yNodePosition

        dao.add(node)
        node.yNodePosition = -10f
        dao.add(node)

        val nodeFromDb = dao.getNodeFromDatabase(node.uniqueId)
//        assert(nodeFromDb.yNodePosition != defaultYValue)
        assertThat(nodeFromDb.yNodePosition).isNotEqualTo(defaultYValue)
    }



    @After
    fun closeDatabase(){
        database.close()
    }
}