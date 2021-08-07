package com.example.rickandmorty.repository

import com.example.rickandmorty.api.RetrofitInstance
import com.example.rickandmorty.model.Character
import retrofit2.Response

class CharacterDetailsRepository {

    suspend fun getCharacterResponse(characterId:Int):Response<Character>{
        return RetrofitInstance.rickAndMortyApi.getCharacter(characterId)
    }
}