package com.github.ferum_bot.games_rawg.viewmodels.main_screen

import androidx.lifecycle.ViewModel
import com.github.ferum_bot.games_rawg.interactors.main_screen.MainScreenInteractor
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

}