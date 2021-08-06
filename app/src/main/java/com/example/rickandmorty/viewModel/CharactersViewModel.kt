package com.example.rickandmorty.viewModel

import androidx.lifecycle.*
import androidx.paging.*
import com.example.rickandmorty.api.RetrofitInstance
import com.example.rickandmorty.api.RickAndMortyApi
import com.example.rickandmorty.dataSource.CharacterListDataSource
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.model.ResponseInfo
import com.example.rickandmorty.repository.CharactersRepository
import com.example.rickandmorty.utils.Constants.Companion.DEFAULT_MAX_PAGE_SIZE
import com.example.rickandmorty.utils.Constants.Companion.DEFAULT_PAGE_SIZE
import kotlinx.coroutines.flow.Flow



class CharactersViewModel(private val charactersRepository: CharactersRepository) : ViewModel() {

    var charactersResponseInfo:MutableLiveData<ResponseInfo> = MutableLiveData()
    var isResponseNotSuccess:MutableLiveData<String> = MutableLiveData()
    var characters:LiveData<PagedList<Character>>? = null
    lateinit var rickAndMortyApi: RickAndMortyApi;
    val pagingConfig=PagingConfig(pageSize = DEFAULT_PAGE_SIZE, maxSize = DEFAULT_MAX_PAGE_SIZE)


    init {
        rickAndMortyApi= RetrofitInstance.rickAndMortyApi
    }

    fun getPagingListData(): Flow<PagingData<Character>> {
        return Pager (config = pagingConfig,
            pagingSourceFactory = {CharacterListDataSource(rickAndMortyApi)}).flow.cachedIn(viewModelScope)
    }





    open class Factory(private val charactersRepository: CharactersRepository): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CharactersViewModel(charactersRepository) as T
        }
    }

    /*  init {
        getAllCharacters()
    }

    private fun getAllCharacters(){

        viewModelScope.launch {
            val response = charactersRepository.getAllCharacters(1)


            if (response.isSuccessful){
                charactersResponseInfo.value =response.body()?.info
                characters.value= response.body()?.results
                //response.body()?.results?.let { charaters.value?.addAll(it) }
                val nextUrl = response.body()?.info?.next

                Log.i("FirstChar", "${charaters.value?.size}")
            }else{
                isResponseNotSuccess.value= response.code().toString()
            }
        }
    }*/

}