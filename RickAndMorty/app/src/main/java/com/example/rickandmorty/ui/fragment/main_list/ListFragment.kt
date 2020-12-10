package com.example.rickandmorty.ui.fragment.main_list

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.R
import com.example.rickandmorty.database.db.MainDatabase
import com.example.rickandmorty.databinding.FragmentListBinding
import com.example.rickandmorty.databinding.FragmentListItemBinding
import com.example.rickandmorty.network.RickAndMortyApiStatus
import com.example.rickandmorty.network.api.RickAndMortyApi
import com.example.rickandmorty.preferences.AppPreferences
import com.example.rickandmorty.repository.CachePolicies
import com.example.rickandmorty.repository.MainRepository
import com.example.rickandmorty.ui.fragment.main_list.recycler_view.CharacterListAdapter
import kotlinx.android.synthetic.main.fragment_list.*

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

        requireOrIssueCharacters()

        val adapter = CharacterListAdapter(viewModel)
        binding.recyclerView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {  newError ->
            if (newError != null) {
                Toast.makeText(requireContext(), newError, Toast.LENGTH_LONG).show()
                viewModel.errorMessageHasShown()
            }
        })

        viewModel.listOfCharacters.observe(viewLifecycleOwner, Observer { newList ->
            (binding.recyclerView.adapter as CharacterListAdapter).submitList(newList)
        })

        viewModel.status.observe(viewLifecycleOwner, Observer { newStatus ->
            when(newStatus) {
                RickAndMortyApiStatus.NOT_ACTIVE -> {
                    binding.recyclerView.visibility = View.GONE
                    binding.statusImage.setImageResource(R.drawable.loading_animation)
                    binding.statusImage.visibility = View.VISIBLE
                }
                RickAndMortyApiStatus.ERROR -> {
                    binding.recyclerView.visibility = View.GONE
                    binding.statusImage.setImageResource(R.drawable.connection_error_image)
                    binding.statusImage.visibility = View.VISIBLE
                    binding.refreshLayout.isRefreshing = false
                }
                RickAndMortyApiStatus.LOADING -> {
                    binding.recyclerView.visibility = View.GONE
                    binding.statusImage.setImageResource(R.drawable.loading_animation)
                    binding.statusImage.visibility = View.VISIBLE
                    binding.refreshLayout.isRefreshing = true
                }
                RickAndMortyApiStatus.DONE -> {
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.statusImage.visibility = View.GONE
                    binding.refreshLayout.isRefreshing = false
                }
            }
        })

        binding.refreshLayout.setOnRefreshListener {
            requireOrIssueCharacters()
            binding.refreshLayout.isRefreshing = true
        }

    }


    private fun requireOrIssueCharacters() {
        val context = requireContext()
        val manager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = manager.activeNetwork
        if (info == null) {
            Toast.makeText(requireContext(), "No Internet Connection", Toast.LENGTH_SHORT).show()
            viewModel.getAllCharacters(CachePolicies.LOCAL)
            return
        }
        viewModel.getAllCharacters(CachePolicies.NETWORK)
    }

}