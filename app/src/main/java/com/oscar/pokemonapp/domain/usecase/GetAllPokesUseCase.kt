package com.oscar.pokemonapp.domain.usecase

import androidx.paging.PagingData
import com.oscar.pokemonapp.commons.BaseUseCase
import com.oscar.pokemonapp.domain.entity.list.GetAllPokesData
import com.oscar.pokemonapp.domain.repository.GetAllPokesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllPokesUseCase @Inject constructor(
    private val getAllRepository: GetAllPokesRepository
): BaseUseCase<Unit,Flow<PagingData<GetAllPokesData>> >{

    override suspend fun execute(input: Unit): Flow<PagingData<GetAllPokesData>> {
        return getAllRepository.getAllPokes()
    }
}