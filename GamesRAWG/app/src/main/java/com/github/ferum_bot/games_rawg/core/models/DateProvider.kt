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

    fun getDateOneMonthLater(): String {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) - 1
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        return convertDateToApiPattern(year, month, dayOfMonth)
    }

    private fun convertDateToApiPattern(year: Int, month: Int, dayOfMonth: Int): String {
        val monthString = when((month + 1).toString().length) {
            1 -> "0${month + 1}"
            2 -> month.toString()
            else ->
                throw IllegalArgumentException("Invalid length of month: $month")
        }
        val dayString = when((dayOfMonth).toString().length) {
            1 -> "0$dayOfMonth"
            2 -> dayOfMonth
            else ->
                throw IllegalArgumentException("Invalid length of day: $dayOfMonth")
        }
        return "$year-$monthString-$dayString"
    }
}