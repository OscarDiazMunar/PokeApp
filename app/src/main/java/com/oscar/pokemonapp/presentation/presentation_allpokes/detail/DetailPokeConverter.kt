package com.oscar.pokemonapp.presentation.presentation_allpokes.detail

import com.oscar.pokemonapp.domain.usecase.DetailPokeUseCase
import com.oscar.pokemonapp.presentation.common.CommonResultConverter
import javax.inject.Inject

class DetailPokeConverter @Inject constructor(

): CommonResultConverter<DetailPokeUseCase.Response, DetailPokeModel>() {
    override fun convertSuccess(data: DetailPokeUseCase.Response): DetailPokeModel {
        return DetailPokeModel(
            id = data.detailPokeData.id,
            abilities = data.detailPokeData.abilities,
            stats = data.detailPokeData.stats,
            types = data.detailPokeData.types,
            forms = data.detailPokeData.forms
        )
    }
}