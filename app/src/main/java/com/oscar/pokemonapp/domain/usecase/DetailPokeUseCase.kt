package com.oscar.pokemonapp.domain.usecase

import com.oscar.pokemonapp.commons.UseCase
import com.oscar.pokemonapp.domain.entity.detail.DetailPokeData
import com.oscar.pokemonapp.domain.entity.list.GetAllPokesData
import com.oscar.pokemonapp.domain.repository.GetAllPokesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DetailPokeUseCase @Inject constructor(
    configuration: UseCase.Configuration,
    private val getAllRepository: GetAllPokesRepository
): UseCase<DetailPokeUseCase.Request, DetailPokeUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> =
        getAllRepository.getDetailPoke(request.id)
            .map {
                Response(it)
            }

    data class Request(val id: String) : UseCase.Request
    data class Response(val detailPokeData: DetailPokeData) : UseCase.Response
}