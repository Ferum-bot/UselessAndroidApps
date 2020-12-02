package com.example.criminalintent.core

import java.util.*

class Utils {
    companion object {
        private const val START_TEXT_FOR_CHOOSE_DATE_BUTTON = "Apply"


        fun getFormattedStringForChooseDateButton(date: Date): String {
            return START_TEXT_FOR_CHOOSE_DATE_BUTTON + "\n" + date.toString()
        }

    }
}