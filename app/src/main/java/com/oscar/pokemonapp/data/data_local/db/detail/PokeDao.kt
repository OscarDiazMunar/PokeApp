package com.oscar.pokemonapp.data.data_local.db.detail

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert

@Dao
interface PokeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokes(pokes: PokeEntity)

    @Query("SELECT * FROM pokes WHERE idImage = :pokeId")
    fun getPokeById(pokeId: String): PokeEntity?

    @Query("SELECT * FROM pokes")
    fun getAllPokes(): List<PokeEntity>

    @Update
    fun updatePoke(poke: PokeEntity)

    @Upsert
    fun insertOrUpdatePoke(poke: PokeEntity)
}