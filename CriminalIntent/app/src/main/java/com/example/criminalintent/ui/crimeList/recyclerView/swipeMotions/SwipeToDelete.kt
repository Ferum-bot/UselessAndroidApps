package com.example.criminalintent.ui.crimeList.recyclerView.swipeMotions

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.criminalintent.ui.crimeList.recyclerView.CrimeListAdapter

class SwipeToDelete(private val adapter: CrimeListAdapter):
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        TODO()
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int){
        val pos = viewHolder.adapterPosition
        adapter.deleteItem(pos)
    }


}