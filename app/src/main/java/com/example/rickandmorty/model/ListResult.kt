package com.example.rickandmorty.model

import com.google.gson.annotations.SerializedName

data class ListResult<T>(
    val info: ResponseInfo,
    val results: List<T>
)
