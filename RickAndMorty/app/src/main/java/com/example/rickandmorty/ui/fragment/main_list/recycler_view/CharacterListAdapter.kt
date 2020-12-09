package com.example.rickandmorty.ui.fragment.main_list.recycler_view

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.rickandmorty.core.models.RickAndMortyCharacter
import com.example.rickandmorty.ui.fragment.main_list.ListViewModel

class CharacterListAdapter (private val viewModel: ListViewModel): ListAdapter<RickAndMortyCharacter, RickAndMortyCharacterViewHolder>(CharacterDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RickAndMortyCharacterViewHolder {
        return RickAndMortyCharacterViewHolder.getViewHolderFrom(parent, viewModel)
    }

    override fun onBindViewHolder(holder: RickAndMortyCharacterViewHolder, position: Int) {
        val character = viewModel.listOfCharacters.value!![position]
        holder.bind(character)
    }
}