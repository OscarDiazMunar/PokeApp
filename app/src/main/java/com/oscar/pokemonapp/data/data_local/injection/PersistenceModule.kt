package com.oscar.pokemonapp.data.data_local.injection

import android.content.Context
import androidx.room.Room
import com.oscar.pokemonapp.data.data_local.db.detail.PokeDao
import com.oscar.pokemonapp.data.data_local.db.detail.PokeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PersistenceModule {
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): PokeDatabase =
        Room.databaseBuilder(
            context,
            PokeDatabase::class.java, "my-database"
        ).build()

    @Provides
    fun providePokeDao(pokeDatabase: PokeDatabase): PokeDao = pokeDatabase.pokeDao()

}