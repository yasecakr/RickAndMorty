package com.example.rickandmorty.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.repository.CharactersRepository
import java.lang.IllegalArgumentException

class CharactersViewModel(private val charactersRepository: CharactersRepository) : ViewModel() {



    open class Factory(private val charactersRepository: CharactersRepository): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CharactersViewModel::class.java)){
                return CharactersViewModel(charactersRepository) as T
            }else{
                throw IllegalArgumentException("ViewModel is not assignable")
            }
        }
    }

}