package com.oscar.pokemonapp.domain.repository

import androidx.paging.PagingData
import com.oscar.pokemonapp.domain.entity.detail.DetailPokeData
import com.oscar.pokemonapp.domain.entity.list.GetAllPokesData
import kotlinx.coroutines.flow.Flow

interface GetAllPokesRepository {
    fun getAllPokes(): Flow<PagingData<GetAllPokesData>>

    fun getDetailPoke(id: String): Flow<DetailPokeData>
}