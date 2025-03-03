package com.oscar.pokemonapp.data.data_local.source

import com.oscar.pokemonapp.data.data_local.db.detail.PokeDao
import com.oscar.pokemonapp.data.data_local.db.detail.PokeEntity
import com.oscar.pokemonapp.data.data_repository.data_source.local.LocalPokeDataSource
import com.oscar.pokemonapp.domain.entity.detail.DetailPokeData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
class LocalPokeDataSourceImpl @Inject constructor(
    private val pokeDao: PokeDao
): LocalPokeDataSource {
    override fun getPoke(): Flow<DetailPokeData> = flow {
        emit(pokeDao.getPokeById("1"))
    }.map {
        convert(it)
    }

    private fun convert(poke: PokeEntity?): DetailPokeData {
        return poke?.let {
            DetailPokeData(
                id = it.id,
                abilities = it.abilities,
                stats = it.stats,
                types = it.types,
                forms = it.forms
            )
        } ?: DetailPokeData.empty()
    }

    override suspend fun addPoke(poke: DetailPokeData) =
        pokeDao.insertOrUpdatePoke(
            PokeEntity(
                id = poke.id,
                idImage = "1",
                abilities = poke.abilities,
                stats = poke.stats,
                types = poke.types,
                forms = poke.forms
            )
        )

}