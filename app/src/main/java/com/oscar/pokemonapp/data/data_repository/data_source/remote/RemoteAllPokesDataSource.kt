package com.oscar.pokemonapp.data.data_repository.data_source.remote

import com.oscar.pokemonapp.domain.entity.detail.DetailPokeData
import com.oscar.pokemonapp.domain.entity.list.GetAllPokesData
import kotlinx.coroutines.flow.Flow

interface RemoteAllPokesDataSource {
    fun getAllPokes(limit: String): Flow<List<GetAllPokesData>>
    fun getDetailPoke(id: String): Flow<DetailPokeData>
}