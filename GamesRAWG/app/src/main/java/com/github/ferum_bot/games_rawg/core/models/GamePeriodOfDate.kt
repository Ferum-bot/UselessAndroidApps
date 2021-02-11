package com.github.ferum_bot.games_rawg.core.models

/**
 * Created by Matvey Popov.
 * Date: 09.02.2021
 * Time: 22:32
 * Project: Games-RAWG
 */
data class GamePeriodOfDate(
    val startDate: String,
    val endDate: String
) {
    override fun toString(): String {
        return "$startDate,$endDate"
    }

    companion object {
        fun getInstanceFromStartAndEndDate(startDate: String, endDate: String): GamePeriodOfDate {
            return GamePeriodOfDate(
                startDate,
                endDate
            )
        }
    }
}
