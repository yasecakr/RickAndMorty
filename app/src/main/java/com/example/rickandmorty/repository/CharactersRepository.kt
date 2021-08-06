package com.example.rickandmorty.repository

import com.example.rickandmorty.api.RetrofitInstance
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.model.ListResult
import retrofit2.Response

class CharactersRepository {

    suspend fun getAllCharacters(page:Int):Response<ListResult<Character>> {
        return RetrofitInstance.rickAndMortyApi.getAllChar(page)
    }
}