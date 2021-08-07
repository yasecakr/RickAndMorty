package com.example.rickandmorty.repository

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmorty.api.RetrofitInstance
import com.example.rickandmorty.dataSource.CharacterListDataSource
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.model.ListResult
import com.example.rickandmorty.utils.Constants
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class CharactersRepository {


    suspend fun getAllCharacters(page:Int):Response<ListResult<Character>> {
        return RetrofitInstance.rickAndMortyApi.getAllChar(page)
    }


}