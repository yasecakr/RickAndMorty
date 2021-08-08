package com.example.rickandmorty.data.model


data class ListResult<T>(
    val info: ResponseInfo,
    val results: ArrayList<T>
)
