package com.oscar.pokemonapp.domain.entity.detail

data class DetailPokeData(
    val id: String,
    val abilities: List<Ability>,
    val stats: List<Stat>,
    val types: List<Type>,
    val forms: List<Form>,
)