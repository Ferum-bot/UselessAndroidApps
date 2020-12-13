package com.example.rickandmorty.ui.fragment.main_list

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.core.add
import com.example.rickandmorty.core.models.RickAndMortyCharacter
import com.example.rickandmorty.network.RickAndMortyApiStatus
import com.example.rickandmorty.repository.CachePolicies
import com.example.rickandmorty.repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import java.security.Policy
import kotlin.Exception

class ListViewModel(private val repository: MainRepository): ViewModel() {

    private val job: Job
    private val uiScope: CoroutineScope

    private val _listOfCharacters: MutableLiveData<MutableList<RickAndMortyCharacter>>
    val listOfCharacters: LiveData<MutableList<RickAndMortyCharacter>>
    get() = _listOfCharacters

    private var downloadedPages: Int = 0

    private val _errorMessage: MutableLiveData<String?>
    val errorMessage: LiveData<String?>
    get() = _errorMessage

    private val _status: MutableLiveData<RickAndMortyApiStatus>
    val status: LiveData<RickAndMortyApiStatus>
    get() = _status

    private val _numberOfPages: MutableLiveData<Int>
    val numberOfPages: LiveData<Int>
    get() = _numberOfPages

    fun getNumberOfAvailableCharacters(): Int = repository.numberOfAvailableCharacters
    fun getNumberOfAvailablePages(): Int = repository.numberOfAvailablePages

    init {
        job = Job()
        uiScope = CoroutineScope(Dispatchers.Main + job)
        _listOfCharacters = MutableLiveData<MutableList<RickAndMortyCharacter>>()
        _errorMessage = MutableLiveData<String?>(null)
        _status = MutableLiveData<RickAndMortyApiStatus>(RickAndMortyApiStatus.NOT_ACTIVE)
        _numberOfPages = MutableLiveData(0)
    }

    private suspend fun getAllCharactersFromDatabaseLaunched() {
        val result = repository.getAllCharactersFromDatabase()
        _listOfCharacters.postValue(result.toMutableList())
        downloadedPages = repository.numberOfAvailablePages
    }

    private suspend fun refreshAllCharactersLaunched() {
        try {
            _status.postValue(RickAndMortyApiStatus.LOADING)

            val pages = downloadedPages
            val listToPost: MutableList<RickAndMortyCharacter> = mutableListOf()
            for (page in 0 until pages) {
                val result = repository.getAllCharactersFromNetwork(page + 1)
                listToPost.add(result)
            }
            _status.postValue(RickAndMortyApiStatus.DONE)
            _listOfCharacters.postValue(listToPost)
        }
        catch (ex: Exception) {
            _status.postValue(RickAndMortyApiStatus.ERROR)
            _errorMessage.postValue("Something went wrong: ${ex.message}")
            Timber.e(ex)
        }
    }

    private fun isValidPage(page: Int): Boolean  {
        if (page != 1 && page > repository.numberOfPages) {
            return false
        }
        if (page <= downloadedPages) {
            return false
        }
        return true
    }

    fun getAllCharactersFromDatabase() {
        uiScope.launch {
            getAllCharactersFromDatabaseLaunched()
        }
    }

    fun refreshAllCharacters() {
        uiScope.launch {
            refreshAllCharactersLaunched()
        }
    }

    fun getAndRefreshAllCharacters() {
        uiScope.launch {
            getAllCharactersFromDatabaseLaunched()
            refreshAllCharactersLaunched()
        }
    }

    fun getCharactersFromNetwork(page: Int) {
        if (!isValidPage(page)) {
            return
        }
        uiScope.launch {
            try {
                _status.postValue(RickAndMortyApiStatus.LOADING)
                val result = repository.getAllCharactersFromNetwork(page)

                downloadedPages++
                val size = result.size
                val listToPost = _listOfCharacters.value
                if (listToPost == null) {
                    _listOfCharacters.postValue(result.toMutableList())
                    _status.postValue(RickAndMortyApiStatus.DONE)
                }
                else {
                    for (i in 0 until size) {
                        listToPost.add(result[i])
                    }
                    _status.postValue(RickAndMortyApiStatus.DONE)
                    _listOfCharacters.postValue(listToPost)
                }
            } catch (ex: Exception) {
                _status.postValue(RickAndMortyApiStatus.ERROR)
                _errorMessage.postValue("Something went wrong: ${ex.message}")
                Timber.e(ex)
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