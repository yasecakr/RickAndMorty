package com.example.rickandmorty.model

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
        var season= episode.filter { it.toString()!="0"}
        val stringBuilder= StringBuilder(season)
        season=stringBuilder.insert(2, "-").toString()
        text+= " ($season)"
        Log.d("REMOVEIF", "$text")
        return text

    }
}