package com.oscar.pokemonapp.data.data_local.db.detail

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.oscar.pokemonapp.domain.entity.detail.Ability
import com.oscar.pokemonapp.domain.entity.detail.Form
import com.oscar.pokemonapp.domain.entity.detail.Stat
import com.oscar.pokemonapp.domain.entity.detail.Type

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromAbilityList(value: List<Ability>): String = gson.toJson(value)

    @TypeConverter
    fun toAbilityList(value: String): List<Ability> {
        val type = object : TypeToken<List<Ability>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromStatList(value: List<Stat>): String = gson.toJson(value)

    @TypeConverter
    fun toStatList(value: String): List<Stat> {
        val type = object : TypeToken<List<Stat>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromTypeList(value: List<Type>): String = gson.toJson(value)

    @TypeConverter
    fun toTypeList(value: String): List<Type> {
        val type = object : TypeToken<List<Type>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromFormList(value: List<Form>): String = gson.toJson(value)

    @TypeConverter
    fun toFormList(value: String): List<Form> {
        val type = object : TypeToken<List<Form>>() {}.type
        return gson.fromJson(value, type)
    }
}