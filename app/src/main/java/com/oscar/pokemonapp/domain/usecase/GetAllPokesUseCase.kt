package com.oscar.pokemonapp.domain.usecase

import com.oscar.pokemonapp.commons.UseCase
import com.oscar.pokemonapp.domain.entity.GetAllPokesData
import com.oscar.pokemonapp.domain.repository.GetAllPokesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllPokesUseCase @Inject constructor(
    configuration: Configuration,
    private val getAllRepository: GetAllPokesRepository
) : UseCase<GetAllPokesUseCase.Request, GetAllPokesUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> =
        getAllRepository.getAllPokes(request.limit)
            .map {
                Response(it)
            }

    data class Request(val limit: String) : UseCase.Request
    data class Response(val dataList: List<GetAllPokesData>) : UseCase.Response
}