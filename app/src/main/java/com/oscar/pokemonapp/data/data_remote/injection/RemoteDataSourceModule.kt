package com.oscar.pokemonapp.data.data_remote.injection

import com.oscar.pokemonapp.data.data_remote.source.RemoteAllPokesDataSourceImpl
import com.oscar.pokemonapp.data.data_repository.data_source.remote.RemoteAllPokesDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {
    @Binds
    abstract fun bindRemoteAllPokesDataSource(remoteAllPokesDataSourceImpl: RemoteAllPokesDataSourceImpl): RemoteAllPokesDataSource
}