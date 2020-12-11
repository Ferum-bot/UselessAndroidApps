package com.example.rickandmorty.ui.fragment.main_list.recycler_view

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.rickandmorty.core.models.RickAndMortyCharacter
import com.example.rickandmorty.network.RickAndMortyApiStatus
import com.example.rickandmorty.ui.fragment.main_list.ListViewModel

class CharacterListAdapter (private val viewModel: ListViewModel): ListAdapter<RickAndMortyCharacter, RickAndMortyCharacterViewHolder>(CharacterDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RickAndMortyCharacterViewHolder {
        return RickAndMortyCharacterViewHolder.getViewHolderFrom(parent, viewModel)
    }

    override fun onBindViewHolder(holder: RickAndMortyCharacterViewHolder, position: Int) {
        val character = viewModel.listOfCharacters.value!![position]
        holder.bind(character)

        val availableNumber = viewModel.getNumberOfAvailableCharacters()
        if (availableNumber - position == 2) {
            Log.i("ListAdapter", "$position $availableNumber")
            val availablePages = viewModel.getNumberOfAvailablePages()
            viewModel.getCharactersFromNetwork(availablePages + 1)
        }
    }
}