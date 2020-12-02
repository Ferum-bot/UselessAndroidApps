package com.example.criminalintent.ui.crimeList.recyclerView

import android.util.Log
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.core.view.MotionEventCompat
import com.example.criminalintent.database.entities.Crime
import com.example.criminalintent.databinding.FragmentCrimeListBinding
import com.example.criminalintent.ui.crimeList.CrimeListFragment
import com.example.criminalintent.ui.crimeList.CrimeListViewModel
import com.example.criminalintent.ui.crimeList.recyclerView.CrimeHolder

class CrimeListAdapter(private val viewModelOfCrimeListFragment: CrimeListViewModel)
    :androidx.recyclerview.widget.ListAdapter<Crime, CrimeHolder>(CrimeDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
        return CrimeHolder.getViewHolderFrom(parent, viewModelOfCrimeListFragment)
    }

    override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
        val crime = viewModelOfCrimeListFragment.crimes.value?.get(position) ?: Crime()
        holder.setValue(crime, position)
    }

    fun deleteItem(pos: Int) {
        viewModelOfCrimeListFragment.deleteCrime(getItem(pos))
    }

}