package com.oscar.pokemonapp.data.data_remote.networking.allpokes.model

data class MainPokesApiModel(
    val count: Long,
    val next: String,
    val previous: String,
    val results: List<Result>,
)
