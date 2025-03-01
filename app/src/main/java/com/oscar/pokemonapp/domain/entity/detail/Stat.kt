package com.oscar.pokemonapp.domain.entity.detail

import com.squareup.moshi.Json

data class Stat(
    @Json(name = "base_stat")
    val baseStat: Long,
    val stat: Stat2,
)