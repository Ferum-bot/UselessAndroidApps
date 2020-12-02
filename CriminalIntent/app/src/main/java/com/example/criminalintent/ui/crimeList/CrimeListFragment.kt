package com.example.criminalintent.ui.crimeList

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.criminalintent.R
import com.example.criminalintent.databinding.FragmentCrimeListBinding
import com.example.criminalintent.ui.crimeList.recyclerView.CrimeHolder
import com.example.criminalintent.ui.crimeList.recyclerView.CrimeListAdapter
import com.example.criminalintent.ui.crimeList.recyclerView.swipeMotions.SwipeToDelete

class CrimeListFragment: Fragment() {

    private lateinit var binding: FragmentCrimeListBinding

    private lateinit var viewModel: CrimeListViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        showActionBar()

        setHasOptionsMenu(true)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_crime_list, container, false)

        val appContext = this.requireContext().applicationContext

        val viewModelFactory = CrimeListViewModelFactory(appContext)

        viewModel = ViewModelProvider(this, viewModelFactory).get(CrimeListViewModel::class.java)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CrimeListAdapter(viewModel)

        viewModel.crimes.observe(viewLifecycleOwner, Observer { newList ->
            adapter.submitList(newList)
        })

        val itemTouchHelper = ItemTouchHelper(SwipeToDelete(adapter))
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)

        binding.recyclerView.adapter = adapter

        viewModel.moveToEditFragmentEvent.observe(viewLifecycleOwner, Observer { newState ->
            if (newState == true) {
                val pos = viewModel.posToMove!!
                viewModel.setMoveToEditFragmentEvent(false)
                findNavController().navigate(CrimeListFragmentDirections.actionCrimeListFragmentToCrimeEditFragment(pos))
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.create_new_crime) {
            val destination = R.id.action_crimeListFragment_to_crimeEditFragment

            findNavController().navigate(destination)
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onStop() {
        super.onStop()

        hideActionBar()
    }

    interface CallBacks {

        fun onCrimeListFragmentClosed()

        fun onCrimeListFragmentOpened()
    }

    private fun showActionBar() {
        activity?.let { activity ->
            (activity as CallBacks).onCrimeListFragmentOpened()
        }
    }

    private fun hideActionBar() {
        activity?.let {
            (activity as CallBacks).onCrimeListFragmentClosed()
        }
    }
}
