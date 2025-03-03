package com.oscar.pokemonapp.data.data_repository.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.oscar.pokemonapp.data.data_local.source.LocalPokeDataSourceImpl
import com.oscar.pokemonapp.data.data_repository.data_source.remote.PokesPagingSource
import com.oscar.pokemonapp.data.data_repository.data_source.remote.RemoteAllPokesDataSource
import com.oscar.pokemonapp.domain.entity.detail.DetailPokeData
import com.oscar.pokemonapp.domain.entity.list.GetAllPokesData
import com.oscar.pokemonapp.domain.repository.GetAllPokesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class GetAllPokesRepositoryImpl @Inject constructor(
    private val remoteAllPokesDataSource: RemoteAllPokesDataSource,
    private val localPokeDataSourceImpl: LocalPokeDataSourceImpl
): GetAllPokesRepository {
    override fun getAllPokes(): Flow<PagingData<GetAllPokesData>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20
            ),
            pagingSourceFactory = { PokesPagingSource(remoteAllPokesDataSource)}
        ).flow
    }
    override fun getDetailPoke(id: String): Flow<DetailPokeData> = remoteAllPokesDataSource.getDetailPoke(id).onEach {
        localPokeDataSourceImpl.addPoke(it)
    }
}