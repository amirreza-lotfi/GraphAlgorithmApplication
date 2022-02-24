package com.example.graphalgorithms

import android.app.Application
import androidx.room.Room
import com.example.graphalgorithms.feature_node.data.repository.EdgeRepositoryImp
import com.example.graphalgorithms.feature_node.data.repository.NodeRepositoryImp
import com.example.graphalgorithms.feature_node.data.source.GraphDatabase
import com.example.graphalgorithms.feature_node.domain.repository.EdgeRepository
import com.example.graphalgorithms.feature_node.domain.repository.NodeRepository
import com.example.graphalgorithms.feature_node.domain.use_case.*
import com.example.graphalgorithms.feature_node.domain.use_case.EdgeUseCases.AddEdgeUseCase
import com.example.graphalgorithms.feature_node.domain.use_case.EdgeUseCases.DeleteEdgeUseCase
import com.example.graphalgorithms.feature_node.domain.use_case.EdgeUseCases.GetEdges
import com.example.graphalgorithms.feature_node.domain.use_case.NodeUseCases.AddNodeUseCase
import com.example.graphalgorithms.feature_node.domain.use_case.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGraphDatabase(app:Application):GraphDatabase{
        return Room.databaseBuilder(
            app,
            GraphDatabase::class.java,
            "graph-db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideNodeRepository(db:GraphDatabase) : NodeRepository {
        return NodeRepositoryImp(db.nodeDou)
    }

    @Provides
    @Singleton
    fun provideEdgeRepository(db:GraphDatabase) : EdgeRepository {
        return EdgeRepositoryImp(db.edgeDou)
    }

    @Provides
    @Singleton
    fun provideUseCases(nodeRepository: NodeRepository, edgeRepository: EdgeRepository): UseCases {
        return UseCases(
            AddNodeUseCase(nodeRepository),
            DeleteNodeUseCase(nodeRepository),
            GetNodesUseCase(nodeRepository),
            GetNodeUseCase(nodeRepository),
            AddEdgeUseCase(edgeRepository),
            DeleteEdgeUseCase(edgeRepository),
            GetEdges(edgeRepository)
        )
    }
}