package com.oscar.pokemonapp.data.data_local.injection

import com.oscar.pokemonapp.data.data_local.source.LocalPokeDataSourceImpl
import com.oscar.pokemonapp.data.data_repository.data_source.local.LocalPokeDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Binds
    abstract fun bindPokeDataSource(localPokeDataSourceImpl: LocalPokeDataSourceImpl): LocalPokeDataSource
}