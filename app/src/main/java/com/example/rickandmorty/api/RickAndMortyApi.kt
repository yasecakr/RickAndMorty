package com.example.rickandmorty.api

import com.example.rickandmorty.model.Character
import com.example.rickandmorty.model.ListResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface  RickAndMortyApi {
    @GET(value = "character")
    suspend fun getAllChar(
        @Query("page") page: Int
    ): Response<ListResult<Character>>

    @GET("character/{characterId}")
    suspend fun getCharacter(
        @Path("characterId") id :Int
    ): Response<Character>


}