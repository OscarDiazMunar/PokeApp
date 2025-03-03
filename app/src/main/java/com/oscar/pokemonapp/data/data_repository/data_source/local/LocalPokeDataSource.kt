package com.oscar.pokemonapp.data.data_repository.data_source.local

import com.oscar.pokemonapp.domain.entity.detail.DetailPokeData
import kotlinx.coroutines.flow.Flow

interface LocalPokeDataSource {
    fun getPoke(): Flow<DetailPokeData>

    suspend fun addPoke(poke: DetailPokeData)
}