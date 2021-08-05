package com.example.rickandmorty.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.model.ListResult
import com.example.rickandmorty.repository.CharactersRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.IllegalArgumentException

class CharactersViewModel(private val charactersRepository: CharactersRepository) : ViewModel() {

    var charactersResponse:MutableLiveData<Response<ListResult<Character>>> = MutableLiveData()
    init {
        getAllCharacters()
    }

    private fun getAllCharacters(){
        viewModelScope.launch {
            val response = charactersRepository.getAllCharacters()
            val a = 1

            if (response.isSuccessful){
                Log.i("FirstChar", "${response.body()}")

            }
        }
    }






    open class Factory(private val charactersRepository: CharactersRepository): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CharactersViewModel(charactersRepository) as T
        }
    }

}