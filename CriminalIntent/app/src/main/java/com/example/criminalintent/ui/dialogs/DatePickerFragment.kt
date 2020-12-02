package com.example.criminalintent.ui.dialogs

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment private constructor(): DialogFragment() {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


        val dateListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

            val resultDate: Date = GregorianCalendar(year, month, dayOfMonth).time

            targetFragment?.let { fragment ->
                (fragment as CallBacks).onDateSelected(resultDate)
            }

        }

        val date = arguments?.getSerializable(ARG_DATE) as Date

        val calendar = Calendar.getInstance()
        calendar.time = date

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(requireContext(), dateListener, year, month, day)
    }

    interface CallBacks {
        fun onDateSelected(date: Date)
    }

    companion object {
        private const val  ARG_DATE = "date"


        fun newInstance(date: Date): DatePickerFragment {
            val args = Bundle().apply {
                putSerializable(ARG_DATE, date)
            }

            return DatePickerFragment().apply {
                arguments = args
            }
        }

    }
}