package com.example.rickandmorty.data

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.data.api.RickAndMortyApi
import com.example.rickandmorty.data.model.Character

class CharacterListDataSource(val rickAndMortyApi: RickAndMortyApi):PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val currentPage: Int = params.key ?: FIRST_PAGE_INDEX
                val response = rickAndMortyApi.getAllChar(currentPage)
                val prevKey= if (currentPage > 2) currentPage - 1 else null
                var nextPageNumber: Int? = null
                if (response.isSuccessful){
                    val uri = Uri.parse(response.body()?.info?.next)
                    val nextPageQuery = uri.getQueryParameter("page")
                    nextPageNumber= nextPageQuery?.toInt()
                }
            LoadResult.Page(data = response.body()?.results as List<Character>, prevKey = prevKey , nextKey = nextPageNumber)

        }catch (e:Exception){

            LoadResult.Error(e)

        }
    }
    companion object{
        private const val FIRST_PAGE_INDEX=1
    }

}