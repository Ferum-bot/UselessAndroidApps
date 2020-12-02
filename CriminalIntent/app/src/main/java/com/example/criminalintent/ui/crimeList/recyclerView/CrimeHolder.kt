package com.example.criminalintent.ui.crimeList.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.criminalintent.R
import com.example.criminalintent.database.entities.Crime
import com.example.criminalintent.databinding.FragmentCrimeItemBinding
import com.example.criminalintent.ui.crimeList.CrimeListViewModel
import com.example.criminalintent.ui.crimeList.recyclerView.RecyclerViewUtils.Companion.getFormattedStringFromDate
import java.util.*

class CrimeHolder private constructor(private val binding: FragmentCrimeItemBinding,
                                    private val crimeListViewModel: CrimeListViewModel): RecyclerView.ViewHolder(binding.root) {

    val imageView: ImageView
        get() = binding.imageDrag

    private fun setTitleText(text: String) {
        binding.titleOfCrime.text = text
    }

    private fun setDateOfCrime(date: Date) {
        binding.dateOfCrime.text = getFormattedStringFromDate(date)
    }

    fun setValue(crime: Crime, pos: Int) {
        setDateOfCrime(crime.date)
        setTitleText(crime.title)


        binding.root.setOnClickListener {
            crimeListViewModel.posToMove = pos
            crimeListViewModel.setMoveToEditFragmentEvent(true)
        }
    }

    companion object {
        fun getViewHolderFrom(parent: ViewGroup, viewModel: CrimeListViewModel): CrimeHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding: FragmentCrimeItemBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_crime_item, parent, false)
            return CrimeHolder(binding, viewModel)
        }
    }
}