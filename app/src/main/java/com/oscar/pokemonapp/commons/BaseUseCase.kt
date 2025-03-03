package com.oscar.pokemonapp.commons

interface BaseUseCase<In, Out>{
    suspend fun execute(input: In): Out
}