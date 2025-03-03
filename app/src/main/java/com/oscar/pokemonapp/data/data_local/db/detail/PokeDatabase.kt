package com.oscar.pokemonapp.data.data_local.db.detail

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [PokeEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PokeDatabase : RoomDatabase() {
    abstract fun pokeDao(): PokeDao

    companion object {
        @Volatile
        private var INSTANCE: PokeDatabase? = null

        fun getDatabase(context: Context): PokeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PokeDatabase::class.java,
                    "poke_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}