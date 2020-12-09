package com.example.rickandmorty.ui.fragment.main_list

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
import com.example.rickandmorty.network.api.RickAndMortyApi
import com.example.rickandmorty.repository.MainRepository
import com.example.rickandmorty.ui.fragment.main_list.recycler_view.CharacterListAdapter

class ListFragment: Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var viewModel: ListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        binding.lifecycleOwner = this

        val database = MainDatabase.getInstance(requireContext().applicationContext)
        val repository = MainRepository(database.rickAndMortyDao, RickAndMortyApi.RickAndMortyService)

        val factory = ListViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(ListViewModel::class.java)

        viewModel.getAllCharacters()

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
    }
}