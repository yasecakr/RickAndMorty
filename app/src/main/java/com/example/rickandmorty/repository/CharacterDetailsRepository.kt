package com.example.rickandmorty.repository

import com.example.rickandmorty.data.api.RetrofitInstance
import com.example.rickandmorty.data.model.Character
import com.example.rickandmorty.data.model.Episode
import retrofit2.Response

class CharacterDetailsRepository {

    suspend fun getCharacterResponse(characterId:Int):Response<Character>{
        return RetrofitInstance.rickAndMortyApi.getCharacter(characterId)
    }
    suspend fun getEpisodeResponse(episodeId:Int):Response<Episode>{
        return RetrofitInstance.rickAndMortyApi.getEpisode(episodeId)
    }
}