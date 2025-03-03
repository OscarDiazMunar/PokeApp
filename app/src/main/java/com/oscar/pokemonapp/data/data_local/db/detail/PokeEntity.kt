package com.oscar.pokemonapp.data.data_local.db.detail

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.oscar.pokemonapp.domain.entity.detail.Ability
import com.oscar.pokemonapp.domain.entity.detail.Form
import com.oscar.pokemonapp.domain.entity.detail.Stat
import com.oscar.pokemonapp.domain.entity.detail.Type

@Entity(tableName = "pokes")
@TypeConverters(Converters::class)
data class PokeEntity(
    @PrimaryKey @ColumnInfo(name = "idImage")val idImage: String,
    val id: String,
    val abilities: List<Ability>,
    val stats: List<Stat>,
    val types: List<Type>,
    val forms: List<Form>,
)