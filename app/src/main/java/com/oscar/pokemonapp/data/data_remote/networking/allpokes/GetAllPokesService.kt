package com.oscar.pokemonapp.data.data_remote.networking.allpokes

import com.oscar.pokemonapp.data.data_remote.networking.allpokes.model.MainPokesApiModel
import com.oscar.pokemonapp.data.data_remote.networking.allpokes.model.detail.DetailPokeDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface GetAllPokesService {
    @GET("pokemon/")
    suspend fun getAllPokes(@Query("limit") limit: String, @Query("offset") offset: String ): MainPokesApiModel

    @GET("pokemon/{id}/")
    suspend fun getDetailPoke(@Path("id") id: String): DetailPokeDTO

}