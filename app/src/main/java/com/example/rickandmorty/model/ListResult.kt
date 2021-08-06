package com.example.rickandmorty.model


data class ListResult<T>(
    val info: ResponseInfo,
    val results: ArrayList<T>
)
