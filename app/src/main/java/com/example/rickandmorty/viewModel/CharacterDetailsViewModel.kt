package com.example.rickandmorty.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.repository.CharacterDetailsRepository
import java.lang.IllegalArgumentException

class CharacterDetailsViewModel(private val characterDetailsRepository: CharacterDetailsRepository) : ViewModel() {



    open class Factory(private val characterDetailsRepository: CharacterDetailsRepository): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CharacterDetailsViewModel::class.java)){
                return CharacterDetailsViewModel(characterDetailsRepository) as T
            }else{
                throw IllegalArgumentException("ViewModel is not assignable")
            }
        }
    }
}