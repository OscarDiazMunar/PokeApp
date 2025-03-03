package com.oscar.pokemonapp.data.data_repository.repository

import com.oscar.pokemonapp.data.data_repository.data_source.local.LocalPokeDataSource
import com.oscar.pokemonapp.domain.entity.detail.DetailPokeData
import com.oscar.pokemonapp.domain.repository.PokeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokeLocalRepositoryImpl @Inject constructor(
    private val localPokeDataSource: LocalPokeDataSource
): PokeRepository {
    override fun getDetailPoke(id: String): Flow<DetailPokeData> =
        localPokeDataSource.getPoke()
}