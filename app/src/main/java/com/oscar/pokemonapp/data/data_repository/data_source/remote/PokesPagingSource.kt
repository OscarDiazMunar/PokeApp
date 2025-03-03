package com.oscar.pokemonapp.data.data_repository.data_source.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.oscar.pokemonapp.domain.entity.list.GetAllPokesData


class PokesPagingSource(
    private val remoteAllPokesDataSource: RemoteAllPokesDataSource
): PagingSource<Int, GetAllPokesData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GetAllPokesData> {
        return try {

            val currentPage = params.key ?: 0
            var allPokes = emptyList<GetAllPokesData>()
            remoteAllPokesDataSource.getAllPokes(offset = currentPage ).collect{
                allPokes = it
            }
            LoadResult.Page(
                data = allPokes,
                prevKey = if (currentPage == 0) null else currentPage - 20,
                nextKey = if (allPokes.isEmpty()) null else currentPage + 20
            )
        }catch (excption: Exception){
            LoadResult.Error(excption)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GetAllPokesData>): Int? {
        return state.anchorPosition
    }
}