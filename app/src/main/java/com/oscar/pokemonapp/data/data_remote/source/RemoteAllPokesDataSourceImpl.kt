package com.oscar.pokemonapp.data.data_remote.source

import com.oscar.pokemonapp.data.data_remote.networking.allpokes.GetAllPokesService
import com.oscar.pokemonapp.data.data_remote.networking.allpokes.model.Result
import com.oscar.pokemonapp.data.data_repository.data_source.remote.RemoteAllPokesDataSource
import com.oscar.pokemonapp.domain.entity.GetAllPokesData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemoteAllPokesDataSourceImpl @Inject constructor(
    private val getAllPokesService: GetAllPokesService
): RemoteAllPokesDataSource {
    override fun getAllPokes(limit: String): Flow<List<GetAllPokesData>> = flow {
        emit(getAllPokesService.getAllPokes(limit, "1"))
    }.map { pokes ->
        convert(pokes.results)
    }

    private fun convert(results: List<Result>): List<GetAllPokesData>  {
        //val allPokesData = emptyList<GetAllPokesData>()
        //https://pokeapi.co/api/v2/type/12/
        val allPokesData = results.map {
            GetAllPokesData(
                id = getLastNumberFromUrl(it.url),
                name = it.name,
                url = it.url
            )
        }
        return allPokesData
    }

    private fun getLastNumberFromUrl(url: String): String {
        return url.trimEnd('/').split("/").lastOrNull()?.toString() ?: ""
    }
}