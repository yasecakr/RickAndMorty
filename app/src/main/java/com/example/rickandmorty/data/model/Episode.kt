package com.example.rickandmorty.data.model

import android.util.Log
import java.lang.StringBuilder

data class Episode(
    val air_date: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
){
    fun episodeDescription():String{
        var text=name
        var season= episode
        val stringBuilder= StringBuilder(season)
        season=stringBuilder.insert(3, "-").toString()
        text+= " ($season)"
        return text

    }
}