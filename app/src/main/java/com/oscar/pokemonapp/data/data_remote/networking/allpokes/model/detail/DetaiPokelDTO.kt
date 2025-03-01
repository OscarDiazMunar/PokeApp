package com.oscar.pokemonapp.data.data_remote.networking.allpokes.model.detail

import com.oscar.pokemonapp.domain.entity.detail.Ability
import com.oscar.pokemonapp.domain.entity.detail.Form
import com.oscar.pokemonapp.domain.entity.detail.Stat
import com.oscar.pokemonapp.domain.entity.detail.Type

data class DetailPokeDTO(
    val id: String,
    val abilities: List<Ability>,
    val stats: List<Stat>,
    val types: List<Type>,
    val forms: List<Form>,
)