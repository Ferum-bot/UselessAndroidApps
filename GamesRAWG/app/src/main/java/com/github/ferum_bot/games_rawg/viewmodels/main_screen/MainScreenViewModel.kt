package com.github.ferum_bot.games_rawg.viewmodels.main_screen

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.github.ferum_bot.core_network.api.parameters.GamesApiParameters
import com.github.ferum_bot.games_rawg.core.models.*
import com.github.ferum_bot.games_rawg.interactors.main_screen.MainScreenInteractor
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.onStart
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

    lateinit var racingGenre: LiveData<PagingData<GameWideItem>>
    lateinit var shooterGenre: LiveData<PagingData<GameWideItem>>
    lateinit var adventureGenre: LiveData<PagingData<GameWideItem>>
    lateinit var actionGenre: LiveData<PagingData<GameWideItem>>
    lateinit var rpgGenre: LiveData<PagingData<GameWideItem>>
    lateinit var fightingGenre: LiveData<PagingData<GameWideItem>>
    lateinit var puzzleGenre: LiveData<PagingData<GameWideItem>>
    lateinit var strategyGenre: LiveData<PagingData<GameWideItem>>

    init {
        val todayDate = DateProvider.getTodayDate()
        val oneMonthLaterDate = DateProvider.getDateOneMonthLater()
        val oneYearLaterDate = DateProvider.getDateOneYearLater()
        val oneYearEarlierDate = DateProvider.getDateOneYearEarlier()

        val oneMonthPastPeriodOfDate = GamePeriodOfDate.getInstanceFromStartAndEndDate(oneMonthLaterDate, todayDate)
        val oneYearPastPeriodOfDate = GamePeriodOfDate.getInstanceFromStartAndEndDate(oneYearLaterDate, todayDate)
        val oneYearWillPassPeriodOfDate = GamePeriodOfDate.getInstanceFromStartAndEndDate(todayDate, oneYearEarlierDate)

        executeCodeWithCatchingExceptions {
            mostAnticipated = mainScreenInteractor
                .getMostAnticipatedGamesFlow(oneYearWillPassPeriodOfDate)
                .cachedIn(viewModelScope)
                .onStart { addThinPlaceHolders(this) }
                .asLiveData()

            latestReleases = mainScreenInteractor
                .getLatestReleasesGamesFlow(oneMonthPastPeriodOfDate)
                .cachedIn(viewModelScope)
                .onStart { addWidePlaceHolders(this) }
                .asLiveData()

            rated = mainScreenInteractor
                .getRatedGamesFlow(oneYearPastPeriodOfDate)
                .cachedIn(viewModelScope)
                .onStart { addWidePlaceHolders(this) }
                .asLiveData()

            racingGenre = mainScreenInteractor
                .getGenreFlow(GamesApiParameters.GenreTypes.RACING, oneYearPastPeriodOfDate)
                .cachedIn(viewModelScope)
                .onStart { addWidePlaceHolders(this) }
                .asLiveData()

            shooterGenre = mainScreenInteractor
                .getGenreFlow(GamesApiParameters.GenreTypes.SHOOTER, oneYearPastPeriodOfDate)
                .cachedIn(viewModelScope)
                .onStart { addWidePlaceHolders(this) }
                .asLiveData()

            adventureGenre = mainScreenInteractor
                .getGenreFlow(GamesApiParameters.GenreTypes.ADVENTURE, oneYearPastPeriodOfDate)
                .cachedIn(viewModelScope)
                .onStart { addWidePlaceHolders(this) }
                .asLiveData()

            actionGenre = mainScreenInteractor
                .getGenreFlow(GamesApiParameters.GenreTypes.ACTION, oneYearPastPeriodOfDate)
                .cachedIn(viewModelScope)
                .onStart { addWidePlaceHolders(this) }
                .asLiveData()

            rpgGenre = mainScreenInteractor
                .getGenreFlow(GamesApiParameters.GenreTypes.RPG, oneYearPastPeriodOfDate)
                .cachedIn(viewModelScope)
                .onStart { addWidePlaceHolders(this) }
                .asLiveData()

            fightingGenre = mainScreenInteractor
                .getGenreFlow(GamesApiParameters.GenreTypes.FIGHTING, oneYearPastPeriodOfDate)
                .cachedIn(viewModelScope)
                .onStart { addWidePlaceHolders(this) }
                .asLiveData()

            puzzleGenre = mainScreenInteractor
                .getGenreFlow(GamesApiParameters.GenreTypes.PUZZLE, oneYearPastPeriodOfDate)
                .cachedIn(viewModelScope)
                .onStart { addWidePlaceHolders(this) }
                .asLiveData()

            strategyGenre = mainScreenInteractor
                .getGenreFlow(GamesApiParameters.GenreTypes.STRATEGY, oneYearPastPeriodOfDate)
                .cachedIn(viewModelScope)
                .onStart { addWidePlaceHolders(this) }
                .asLiveData()
        }
    }

    fun errorMessageHasShown() {
        _errorMessage.value = null
    }

    private suspend fun addThinPlaceHolders(flowCollector: FlowCollector<PagingData<GameThinItem>>) {
        val listOfPlaceholders = GameThinItem.getListOfPlaceholders()
        val pagingData = PagingData.from(listOfPlaceholders)
        flowCollector.emit(pagingData)
    }

    private suspend fun addWidePlaceHolders(flowCollector: FlowCollector<PagingData<GameWideItem>>) {
        val listOfPlaceholders = GameWideItem.getListOfPlaceholders()
        val pagingData = PagingData.from(listOfPlaceholders)
        flowCollector.emit(pagingData)
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