package com.example.rickandmorty.viewModel

import android.net.Uri
import android.net.UrlQuerySanitizer
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.api.RetrofitInstance
import com.example.rickandmorty.api.RickAndMortyApi
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.model.Episode
import com.example.rickandmorty.repository.CharacterDetailsRepository
import com.example.rickandmorty.utils.Constants
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.IllegalArgumentException

class CharacterDetailsViewModel(
    private val characterId:Int,
    private val characterDetailsRepository: CharacterDetailsRepository) : ViewModel() {

    val character:MutableLiveData<Character> = MutableLiveData()
    val isError: MutableLiveData<Boolean> = MutableLiveData()
    val episodes:MutableLiveData<ArrayList<Episode>> = MutableLiveData(ArrayList())

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

     fun getEpisodes(){
         val episodesArray:ArrayList<Episode> = ArrayList()
         viewModelScope.launch {
            character.value?.episode?.forEach { url->
            val uri = Uri.parse(url).encodedPath.toString()
                val array = uri.split("/").toTypedArray()
                val episodeId = array[array.size-1].toInt()
                val episodeResponse = characterDetailsRepository.getEpisodeResponse(episodeId)
                if (episodeResponse.isSuccessful){
                    episodesArray.add(episodeResponse.body()!!)
                    episodeResponse.body()?.let { episodes.value?.add(it) }
                }else{
                    Log.d("Episode","Response not success ${episodeResponse.code()}")
                }
            }
             episodes.value?.addAll(episodesArray)
         }
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