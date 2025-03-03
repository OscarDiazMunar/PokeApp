package com.oscar.pokemonapp.domain.repository

import com.oscar.pokemonapp.domain.entity.detail.DetailPokeData
import kotlinx.coroutines.flow.Flow

interface PokeRepository {
    fun getDetailPoke(id: String): Flow<DetailPokeData>
}