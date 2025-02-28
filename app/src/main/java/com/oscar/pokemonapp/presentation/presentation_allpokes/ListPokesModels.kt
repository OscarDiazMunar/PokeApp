package com.oscar.pokemonapp.presentation.presentation_allpokes

data class ListPokesModel(
    val items: List<ListItemPokesModel>,
)

data class ListItemPokesModel(
    val id: String,
    val name: String,
    val url: String,
)