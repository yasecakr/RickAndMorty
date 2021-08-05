package com.example.rickandmorty.api

import com.example.rickandmorty.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val rickAndMortyApi:RickAndMortyApi by lazy {
        retrofit.create(RickAndMortyApi::class.java)
    }

}