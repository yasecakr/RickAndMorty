package com.example.rickandmorty.data.api

import com.example.rickandmorty.data.model.Character
import com.example.rickandmorty.data.model.Episode
import com.example.rickandmorty.data.model.ListResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface  RickAndMortyApi {
    @GET(value = "/api/character")
    suspend fun getAllChar(
        @Query("page") page: Int
    ): Response<ListResult<Character>>

    @GET("/api/character/{characterId}")
    suspend fun getCharacter(
        @Path("characterId") id :Int
    ): Response<Character>

    @GET("/api/episode/{episodeId}")
    suspend fun getEpisode(
    @Path("episodeId") episodeId :Int
    ):Response<Episode>



}