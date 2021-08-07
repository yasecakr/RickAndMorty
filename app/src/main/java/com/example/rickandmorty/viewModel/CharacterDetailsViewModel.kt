package com.example.rickandmorty.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.repository.CharacterDetailsRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class CharacterDetailsViewModel(
    private val characterId:Int,
    private val characterDetailsRepository: CharacterDetailsRepository) : ViewModel() {
    val character:MutableLiveData<Character> = MutableLiveData()
    val isError: MutableLiveData<Boolean> = MutableLiveData()
    init {
        initCharacter()
    }

    private fun initCharacter() {
        viewModelScope.launch {
           val response = characterDetailsRepository.getCharacterResponse(characterId)
            if (response.isSuccessful){
                character.value= response.body()
            }else{
                isError.value = true
            }
        }
    }
    fun getCharacterDescription():String{
        return character.value?.status.toString() + "," + character.value?.species.toString()
    }


    open class Factory(private val characterId:Int, private val characterDetailsRepository: CharacterDetailsRepository): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CharacterDetailsViewModel::class.java)){
                return CharacterDetailsViewModel(characterId,characterDetailsRepository) as T
            }else{
                throw IllegalArgumentException("ViewModel is not assignable")
            }
        }
    }
}