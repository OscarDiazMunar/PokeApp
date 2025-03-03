package com.oscar.pokemonapp.data.data_repository.data_source.remote

import com.oscar.pokemonapp.commons.Constants
import com.oscar.pokemonapp.domain.entity.detail.DetailPokeData
import com.oscar.pokemonapp.domain.entity.list.GetAllPokesData
import kotlinx.coroutines.flow.Flow

interface RemoteAllPokesDataSource {
    fun getAllPokes(limit: String = Constants.LIMIT_PAGE, offset: Int): Flow<List<GetAllPokesData>>
    fun getDetailPoke(id: String): Flow<DetailPokeData>
}