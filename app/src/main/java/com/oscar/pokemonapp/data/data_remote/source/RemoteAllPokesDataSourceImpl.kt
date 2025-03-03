package com.oscar.pokemonapp.data.data_remote.source

import com.oscar.pokemonapp.commons.Constants.LIMIT_PAGE
import com.oscar.pokemonapp.data.data_remote.networking.allpokes.GetAllPokesService
import com.oscar.pokemonapp.data.data_remote.networking.allpokes.model.Result
import com.oscar.pokemonapp.data.data_remote.networking.allpokes.model.detail.DetailPokeDTO
import com.oscar.pokemonapp.data.data_repository.data_source.remote.RemoteAllPokesDataSource
import com.oscar.pokemonapp.domain.entity.detail.DetailPokeData
import com.oscar.pokemonapp.domain.entity.list.GetAllPokesData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemoteAllPokesDataSourceImpl @Inject constructor(
    private val getAllPokesService: GetAllPokesService
): RemoteAllPokesDataSource {
    override fun getAllPokes(limit: String, offset: Int): Flow<List<GetAllPokesData>> = flow {
        emit(getAllPokesService.getAllPokes(LIMIT_PAGE, offset.toString()))
    }.map { pokes ->
        convert(pokes.results)
    }

    override fun getDetailPoke(id: String): Flow<DetailPokeData> = flow {
        emit(getAllPokesService.getDetailPoke(id))
    }.map {
        convertDetailDTO(it)
    }

    private fun convertDetailDTO(detailPokeDTO: DetailPokeDTO): DetailPokeData {
        return DetailPokeData(
            id = detailPokeDTO.id,
            abilities = detailPokeDTO.abilities,
            stats = detailPokeDTO.stats,
            types = detailPokeDTO.types,
            forms = detailPokeDTO.forms
        )
    }

    private fun convert(results: List<Result>): List<GetAllPokesData>  {

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