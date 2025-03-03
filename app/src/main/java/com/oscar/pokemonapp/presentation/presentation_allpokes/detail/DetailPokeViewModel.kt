package com.oscar.pokemonapp.presentation.presentation_allpokes.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.pokemonapp.domain.usecase.DetailPokeUseCase
import com.oscar.pokemonapp.domain.usecase.LocalGetPokeUseCase
import com.oscar.pokemonapp.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailPokeViewModel @Inject constructor(
    private val getDetailPokeUseCase: DetailPokeUseCase,
    private val pokeUseCase: LocalGetPokeUseCase,
    private val converter: DetailPokeConverter
): ViewModel() {
    private val _getDetalFLow = MutableStateFlow<UiState<DetailPokeModel>>(UiState.Loading)
    val getDetailFlow: StateFlow<UiState<DetailPokeModel>> = _getDetalFLow

    fun fetchDetailPoke(id: String){
        if (id.equals("local")){
            loadLocalDetailPoke(id)
        } else {
            loadDetailPoke(id)
        }
    }
    fun loadDetailPoke(id: String){
        viewModelScope.launch {
            getDetailPokeUseCase.execute(DetailPokeUseCase.Request(id))
                .map {
                    converter.convert(it)
                }
                .collect{
                    _getDetalFLow.value = it
                }
        }
    }

    fun loadLocalDetailPoke(id: String){
        viewModelScope.launch {
            pokeUseCase.execute(DetailPokeUseCase.Request(id))
                .map {
                    converter.convert(it)
                }
                .collect{
                    _getDetalFLow.value = it
                }
        }
    }
}