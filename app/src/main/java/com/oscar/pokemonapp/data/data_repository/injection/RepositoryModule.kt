package com.oscar.pokemonapp.data.data_repository.injection

import com.oscar.pokemonapp.data.data_repository.repository.GetAllPokesRepositoryImpl
import com.oscar.pokemonapp.data.data_repository.repository.GetPokeLocalRepositoryImpl
import com.oscar.pokemonapp.domain.repository.GetAllPokesRepository
import com.oscar.pokemonapp.domain.repository.PokeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindGetAllPokesRepository(getAllPokesRepositoryImpl: GetAllPokesRepositoryImpl): GetAllPokesRepository

    @Binds
    abstract fun bindGetPokeLocalRepositoryImpl(getPokeLocalRepositoryImpl: GetPokeLocalRepositoryImpl): PokeRepository
}