package com.oscar.pokemonapp.presentation.presentation_allpokes.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.oscar.pokemonapp.domain.entity.list.GetAllPokesData
import com.oscar.pokemonapp.domain.usecase.GetAllPokesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListAllPokesViewModel @Inject constructor(
    private val getAllPokesUseCase: GetAllPokesUseCase,
): ViewModel() {
    private val _getListFlow: MutableStateFlow<PagingData<GetAllPokesData>> = MutableStateFlow(value = PagingData.empty())
    val getListFlow: MutableStateFlow<PagingData<GetAllPokesData>> get() = _getListFlow
    init {
        viewModelScope.launch {
            getAllPokes()
        }
    }
    suspend fun getAllPokes(){
        getAllPokesUseCase.execute(Unit)
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect{
                _getListFlow.value = it
            }
    }
}