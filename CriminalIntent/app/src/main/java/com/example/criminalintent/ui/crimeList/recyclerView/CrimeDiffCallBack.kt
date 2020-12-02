package com.example.criminalintent.ui.crimeList.recyclerView

import androidx.recyclerview.widget.DiffUtil
import com.example.criminalintent.database.entities.Crime

class CrimeDiffCallBack: DiffUtil.ItemCallback<Crime>() {
    override fun areItemsTheSame(oldItem: Crime, newItem: Crime): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Crime, newItem: Crime): Boolean {
        return oldItem == newItem
    }
}