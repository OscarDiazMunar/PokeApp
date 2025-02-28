package com.oscar.pokemonapp.presentation.presentation_allpokes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.pokemonapp.domain.usecase.GetAllPokesUseCase
import com.oscar.pokemonapp.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListAllPokesViewModel @Inject constructor(
    private val getAllPokesUseCase: GetAllPokesUseCase,
    private val converter: AllPokesConverter
): ViewModel() {
    private val _getListFlow = MutableStateFlow<UiState<ListPokesModel>>(UiState.Loading)
    val getListFlow: StateFlow<UiState<ListPokesModel>> = _getListFlow

    fun loadAllPokes(){
        viewModelScope.launch {
            getAllPokesUseCase.execute(GetAllPokesUseCase.Request("20"))
                .map {
                    converter.convert(it)
                }
                .collect{
                    _getListFlow.value = it
                }
        }
    }
}