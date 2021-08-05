package com.example.rickandmorty.api

import com.example.rickandmorty.model.Character
import com.example.rickandmorty.model.ListResult
import retrofit2.Response
import retrofit2.http.GET

interface  RickAndMortyApi {
    @GET(value = "character")
    suspend fun getAllChar(): Response<ListResult<Character>>
}