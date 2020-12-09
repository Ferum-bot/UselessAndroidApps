package com.example.rickandmorty.ui.fragment.main_list

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.core.models.RickAndMortyCharacter
import com.example.rickandmorty.repository.CachePolicies
import com.example.rickandmorty.repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.security.Policy

class ListViewModel(private val repository: MainRepository): ViewModel() {

    private val job: Job
    private val uiScope: CoroutineScope

    private val _listOfCharacters: MutableLiveData<List<RickAndMortyCharacter>>
    val listOfCharacters: LiveData<List<RickAndMortyCharacter>>
    get() = _listOfCharacters

    private val _listOfImageUrl: MutableLiveData<List<String>>
    val listOfImageUrl: LiveData<List<String>>
    get() = _listOfImageUrl

    private val _errorMessage: MutableLiveData<String?>
    val errorMessage: LiveData<String?>
    get() = _errorMessage

    init {
        job = Job()
        uiScope = CoroutineScope(Dispatchers.Main + job)
        _listOfCharacters = MutableLiveData<List<RickAndMortyCharacter>>()
        _listOfImageUrl = MutableLiveData<List<String>>()
        _errorMessage = MutableLiveData<String?>(null)
    }

    fun getAllCharacters() {
        uiScope.launch {
            try {
                val result = repository.getCharactersFromPage(1, CachePolicies.NETWORK)
                _listOfCharacters.postValue(result)
            }
            catch (ex: Exception) {
                _errorMessage.value = "Something went wrong: ${ex.message}"
                Log.e("ListViewModel", "${ex.message}")
            }
        }
    }

    fun errorMessageHasShown() {
        _errorMessage.value = null
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}