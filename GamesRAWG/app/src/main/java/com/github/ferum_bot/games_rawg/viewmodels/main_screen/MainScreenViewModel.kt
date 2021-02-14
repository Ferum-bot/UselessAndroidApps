package com.github.ferum_bot.games_rawg.viewmodels.main_screen

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.github.ferum_bot.games_rawg.core.models.*
import com.github.ferum_bot.games_rawg.interactors.main_screen.MainScreenInteractor
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Matvey Popov.
 * Date: 10.02.2021
 * Time: 23:23
 * Project: Games-RAWG
 */
class MainScreenViewModel @Inject constructor(
    private val mainScreenInteractor: MainScreenInteractor
): ViewModel() {

    private val _errorMessage: MutableLiveData<String?> = MutableLiveData(null)
    val errorMessage: LiveData<String?>
    get() = _errorMessage

    lateinit var latestReleases: LiveData<PagingData<GameWideItem>>
    lateinit var mostAnticipated: LiveData<PagingData<GameThinItem>>
    lateinit var rated: LiveData<PagingData<GameWideItem>>

    init {
        val todayDate = DateProvider.getTodayDate()
        val oneYearLaterDate = DateProvider.getDateOneYearLater()
        val periodOfDate = GamePeriodOfDate.getInstanceFromStartAndEndDate(todayDate, oneYearLaterDate)

        executeCodeWithCatchingExceptions {
            mostAnticipated = mainScreenInteractor
                .getMostAnticipatedGamesFlow(periodOfDate)
                .cachedIn(viewModelScope)
                .asLiveData()
            latestReleases = mainScreenInteractor
                .getLatestReleasesGamesFlow(periodOfDate)
                .cachedIn(viewModelScope)
                .asLiveData()
            rated = mainScreenInteractor
                .getRatedGamesFlow(periodOfDate)
                .cachedIn(viewModelScope)
                .asLiveData()
        }
    }

    fun errorMessageHasShown() {
        _errorMessage.value = null
    }

    private fun launchDataLoading(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                block()
            }
            catch (error: Throwable) {
                _errorMessage.postValue(error.message)
            }
        }
    }

    private fun executeCodeWithCatchingExceptions(code: () -> Unit) {
        try {
            code()
        }
        catch (ex: Throwable) {
            _errorMessage.postValue(ex.message)
        }
    }
}