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
    var isResponseNotSuccess:MutableLiveData<Boolean> = MutableLiveData()
    var charaters:MutableLiveData<List<Character>> =MutableLiveData<List<Character>>()

    init {
        getAllCharacters()
    }

    private fun getAllCharacters(){
        viewModelScope.launch {
            val response = charactersRepository.getAllCharacters()
            charactersResponse.value =response

            if (response.isSuccessful){
                 charaters.value=response.body()?.results
                Log.i("FirstChar", "${response.body()}")
            }else{
                isResponseNotSuccess.value= true
            }
        }
    }






    open class Factory(private val charactersRepository: CharactersRepository): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CharactersViewModel(charactersRepository) as T
        }
    }

}