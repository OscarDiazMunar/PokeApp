package com.oscar.pokemonapp.domain.usecase

import com.oscar.pokemonapp.commons.UseCase
import com.oscar.pokemonapp.domain.entity.detail.DetailPokeData
import com.oscar.pokemonapp.domain.repository.PokeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalGetPokeUseCase @Inject constructor(
    configuration: UseCase.Configuration,
    private val pokeRepository: PokeRepository
): UseCase<DetailPokeUseCase.Request, DetailPokeUseCase.Response>(configuration)  {
    override fun process(request: DetailPokeUseCase.Request): Flow<DetailPokeUseCase.Response> {
        return pokeRepository.getDetailPoke(request.id)
            .map {
                DetailPokeUseCase.Response(it)
            }
    }

    data class Request(val id: String) : UseCase.Request
    data class Response(val detailPokeData: DetailPokeData) : UseCase.Response
}