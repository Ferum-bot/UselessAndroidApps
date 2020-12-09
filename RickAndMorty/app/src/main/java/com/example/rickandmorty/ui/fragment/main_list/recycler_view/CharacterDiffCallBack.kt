package com.example.rickandmorty.ui.fragment.main_list.recycler_view

import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmorty.core.models.RickAndMortyCharacter

class CharacterDiffCallBack: DiffUtil.ItemCallback<RickAndMortyCharacter>() {

    override fun areItemsTheSame(oldItem: RickAndMortyCharacter, newItem: RickAndMortyCharacter): Boolean {
        TODO("Not yet implemented")
    }

    override fun areContentsTheSame(oldItem: RickAndMortyCharacter, newItem: RickAndMortyCharacter): Boolean {
        TODO("Not yet implemented")
    }

}