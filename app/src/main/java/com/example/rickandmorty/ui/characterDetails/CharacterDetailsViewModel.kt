package com.example.rickandmorty.ui.characterDetails

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.model.Character
import com.example.rickandmorty.data.model.Episode
import com.example.rickandmorty.repository.CharacterDetailsRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class CharacterDetailsViewModel(
    private val characterId:Int,
    private val characterDetailsRepository: CharacterDetailsRepository) : ViewModel() {

    val character:MutableLiveData<Character> = MutableLiveData()
    val isError: MutableLiveData<Boolean> = MutableLiveData()
    val episodes:MutableLiveData<ArrayList<Episode>> = MutableLiveData(ArrayList())
    val showEpisodes: MutableLiveData<Boolean> = MutableLiveData(false)
    val episodesReady:MutableLiveData<Boolean> = MutableLiveData(false)

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
    suspend fun getAllEpisodes(){
        episodesReady.value = false

         character.value?.episode?.forEach { url->
             val uri = Uri.parse(url).encodedPath.toString()
             val array = uri.split("/").toTypedArray()
             val episodeId = array[array.size - 1].toInt()
             val episodeResponse = characterDetailsRepository.getEpisodeResponse(episodeId)
             if (episodeResponse.isSuccessful) {
                 episodeResponse.body()?.let { episodes.value?.add(it) }
             } else {
                 Log.d("Episode", "Response not success ${episodeResponse.code()}")
             }
         }
        episodesReady.value = true


        /* var iterator =0
         val allcall:ArrayList<String> = character.value?.episode as ArrayList<String>
         val size = allcall.size
         var flag = true
         var pageNumber= 10

         while (iterator != pageNumber && flag) {
             viewModelScope.launch {
                 val uri = Uri.parse(allcall[iterator]).encodedPath.toString()
                 val array = uri.split("/").toTypedArray()
                 val episodeId = array[array.size - 1].toInt()
                 val episodeResponse = characterDetailsRepository.getEpisodeResponse(episodeId)
                 if (episodeResponse.isSuccessful) {
                     episodeResponse.body()?.let { episodes.value?.add(it) }
                     Log.d("Episode", "Response not success ${episodeResponse.code()}")
                 } else {
                     Log.d("Episode", "Response not success ${episodeResponse.code()}")
                 }
                 iterator++
                 if (iterator==pageNumber) pageNumber += 10
                 else if (iterator== size-1) flag=false
             }
         }*/
    }
    fun episodesIsShow(){
        when(showEpisodes.value){
            true -> showEpisodes.value= false
            false -> showEpisodes.value= true
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