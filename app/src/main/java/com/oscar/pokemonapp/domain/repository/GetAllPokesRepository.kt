package com.oscar.pokemonapp.domain.repository

import com.oscar.pokemonapp.domain.entity.detail.DetailPokeData
import com.oscar.pokemonapp.domain.entity.list.GetAllPokesData
import kotlinx.coroutines.flow.Flow

interface GetAllPokesRepository {
    fun getAllPokes(limit: String): Flow<List<GetAllPokesData>>

    fun getDetailPoke(id: String): Flow<DetailPokeData>
}