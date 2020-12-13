package com.example.rickandmorty.ui.fragment.main_list

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.R
import com.example.rickandmorty.core.Variables
import com.example.rickandmorty.database.db.MainDatabase
import com.example.rickandmorty.databinding.FragmentListBinding
import com.example.rickandmorty.network.RickAndMortyApiStatus
import com.example.rickandmorty.network.api.RickAndMortyApi
import com.example.rickandmorty.preferences.AppPreferences
import com.example.rickandmorty.repository.MainRepository
import com.example.rickandmorty.ui.fragment.main_list.recycler_view.CharacterListAdapter

class ListFragment: Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var viewModel: ListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        binding.lifecycleOwner = this

        val database = MainDatabase.getInstance(requireContext().applicationContext)
        val preferences = AppPreferences.getAppPreferences(requireContext().applicationContext)
        val repository = MainRepository(database.rickAndMortyDao, RickAndMortyApi.RickAndMortyService, preferences)

        val factory = ListViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(ListViewModel::class.java)

        val adapter = CharacterListAdapter(viewModel)
        binding.recyclerView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireOrIssueCharacters()

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {  newError ->
            if (newError != null) {
                Toast.makeText(requireContext(), newError, Toast.LENGTH_SHORT).show()
                viewModel.errorMessageHasShown()
            }
        })

        viewModel.listOfCharacters.observe(viewLifecycleOwner, Observer { newList ->
           (binding.recyclerView.adapter as CharacterListAdapter).submitList(newList)
            (binding.recyclerView.adapter as CharacterListAdapter).notifyDataSetChanged()
        })

        viewModel.status.observe(viewLifecycleOwner, Observer { newStatus ->
            when(newStatus) {
                RickAndMortyApiStatus.NOT_ACTIVE -> {
                    if (viewModel.getNumberOfAvailablePages() == 0) {
                        showErrorImage()
                    }
                    else {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.statusImage.visibility = View.GONE
                    }
                }
                RickAndMortyApiStatus.ERROR -> {
                    binding.refreshLayout.isRefreshing = false
                    if (viewModel.getNumberOfAvailablePages() == 0) {
                        showErrorImage()
                    }
                    else {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.statusImage.visibility = View.GONE
                    }
                }
                RickAndMortyApiStatus.LOADING -> {
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.refreshLayout.isRefreshing = true
                    binding.statusImage.visibility = View.GONE
                }
                RickAndMortyApiStatus.DONE -> {
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.statusImage.visibility = View.GONE
                    binding.refreshLayout.isRefreshing = false
                }
            }
        })

        binding.refreshLayout.setOnRefreshListener {
            binding.refreshLayout.isRefreshing = true
            if (!Variables.isNetworkConnectionAvailable) {
                showNoInternetConnectionMessage()
                if (viewModel.getNumberOfAvailablePages() == 0) {
                    showErrorImage()
                }
                binding.refreshLayout.isRefreshing = false
            }
            else {
                if (viewModel.getNumberOfAvailablePages() == 0) {
                    viewModel.getCharactersFromNetwork(1)
                    binding.refreshLayout.isRefreshing = false
                }
                else {
                    viewModel.refreshAllCharacters()
                }
            }
        }
    }

    private fun requireOrIssueCharacters() {
        val availablePages = viewModel.getNumberOfAvailablePages()
        if (!Variables.isNetworkConnectionAvailable) {
            showNoInternetConnectionMessage()
            if (availablePages == 0) {
                showErrorImage()
                return
            }
            viewModel.getAllCharactersFromDatabase()
            return
        }
        if (availablePages == 0) {
            viewModel.getCharactersFromNetwork(1)
        }
        else {
            viewModel.getAndRefreshAllCharacters()
        }
    }

    private fun showNoInternetConnectionMessage() {
        Toast.makeText(context, "No Internet Connection!", Toast.LENGTH_SHORT).show()
    }

    private fun showErrorImage() {
        binding.recyclerView.visibility = View.GONE
        binding.statusImage.visibility = View.VISIBLE
    }

}