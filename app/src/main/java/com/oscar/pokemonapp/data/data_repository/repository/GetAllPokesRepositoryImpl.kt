package com.oscar.pokemonapp.data.data_repository.repository

import com.oscar.pokemonapp.data.data_repository.data_source.remote.RemoteAllPokesDataSource
import com.oscar.pokemonapp.domain.entity.detail.DetailPokeData
import com.oscar.pokemonapp.domain.entity.list.GetAllPokesData
import com.oscar.pokemonapp.domain.repository.GetAllPokesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllPokesRepositoryImpl @Inject constructor(
    private val remoteAllPokesDataSource: RemoteAllPokesDataSource
): GetAllPokesRepository {
    override fun getAllPokes(limit: String): Flow<List<GetAllPokesData>> = remoteAllPokesDataSource.getAllPokes(limit)
    override fun getDetailPoke(id: String): Flow<DetailPokeData> = remoteAllPokesDataSource.getDetailPoke(id)
}