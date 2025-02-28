package com.oscar.pokemonapp.data.data_repository.data_source.remote

import com.oscar.pokemonapp.domain.entity.GetAllPokesData
import kotlinx.coroutines.flow.Flow

interface RemoteAllPokesDataSource {
    fun getAllPokes(limit: String): Flow<List<GetAllPokesData>>
}