package com.oscar.pokemonapp.presentation.presentation_allpokes.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.oscar.pokemonapp.domain.entity.list.GetAllPokesData
import com.oscar.pokemonapp.domain.usecase.GetAllPokesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListAllPokesViewModel @Inject constructor(
    private val getAllPokesUseCase: GetAllPokesUseCase,
): ViewModel() {
    private val searchQuery = MutableStateFlow("")

    private val _getListFlow: MutableStateFlow<PagingData<GetAllPokesData>> = MutableStateFlow(value = PagingData.empty())
    val getListFlow: MutableStateFlow<PagingData<GetAllPokesData>> get() = _getListFlow

    init {
        getAllPokes()
    }

    fun getAllPokes(){
        viewModelScope.launch {
            searchQuery.
                flatMapLatest { query ->
                   getAllPokesUseCase.execute(Unit)
                       .distinctUntilChanged()
                       .map { pagingData ->
                           pagingData.filter { it.name.contains(query, ignoreCase = true) }
                       }
                       .cachedIn(viewModelScope)
                }
                .collect{
                    _getListFlow.value = it
                }
        }

    }

    fun setSearchQuery(query: String) {
        searchQuery.value = query
    }
}