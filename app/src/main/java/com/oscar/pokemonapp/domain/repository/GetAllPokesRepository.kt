package com.oscar.pokemonapp.domain.repository

import com.oscar.pokemonapp.domain.entity.GetAllPokesData
import kotlinx.coroutines.flow.Flow

interface GetAllPokesRepository {
    fun getAllPokes(limit: String): Flow<List<GetAllPokesData>>
}