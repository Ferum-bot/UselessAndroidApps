package com.github.ferum_bot.games_rawg.core.models

import java.time.DayOfWeek
import java.util.*

/**
 * Created by Matvey Popov.
 * Date: 11.02.2021
 * Time: 21:30
 * Project: Games-RAWG
 */
object DateProvider {

    fun getTodayDate(): String {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        return convertDateToApiPattern(year, month, dayOfMonth)
    }

    fun getDateOneYearLater(): String {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR) - 1
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        return convertDateToApiPattern(year, month, dayOfMonth)
    }

    private fun convertDateToApiPattern(year: Int, month: Int, dayOfWeek: Int): String {
        return "$year-${month + 1}-${dayOfWeek + 1}"
    }
}