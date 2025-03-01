package com.oscar.pokemonapp.presentation.presentation_allpokes.list

import android.content.Context
import com.oscar.pokemonapp.domain.usecase.GetAllPokesUseCase
import com.oscar.pokemonapp.presentation.common.CommonResultConverter
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AllPokesConverter @Inject constructor(
    @ApplicationContext private val context: Context
): CommonResultConverter<GetAllPokesUseCase.Response, ListPokesModel>() {
    override fun convertSuccess(data: GetAllPokesUseCase.Response): ListPokesModel {
        return ListPokesModel(
            items = data.dataList.map {
                ListItemPokesModel(
                    id = it.id,
                    name = it.name,
                    url = it.url
                )
            }
        )
    }
}