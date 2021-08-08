package com.example.rickandmorty.repository

import android.util.Log
import com.example.rickandmorty.api.RetrofitInstance
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.model.Episode
import retrofit2.Response

class CharacterDetailsRepository {

    suspend fun getCharacterResponse(characterId:Int):Response<Character>{
        return RetrofitInstance.rickAndMortyApi.getCharacter(characterId)
    }
    suspend fun getEpisodeResponse(episodeId:Int):Response<Episode>{
        return RetrofitInstance.rickAndMortyApi.getEpisode(episodeId)
    }
}