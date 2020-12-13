package com.example.rickandmorty.ui.fragment.main_list.recycler_view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.core.models.RickAndMortyCharacter
import com.example.rickandmorty.databinding.FragmentListBinding
import com.example.rickandmorty.databinding.FragmentListItemBinding
import com.example.rickandmorty.ui.fragment.main_list.ListViewModel

class RickAndMortyCharacterViewHolder private constructor(private val viewModel: ListViewModel,
                                                          private val binding: FragmentListItemBinding,
                                                          private val rickAndMortyCharacter: RickAndMortyCharacter): RecyclerView.ViewHolder(binding.root) {

    init {
        binding.character = rickAndMortyCharacter
        setStatus(rickAndMortyCharacter)
        setName(rickAndMortyCharacter)
        binding.executePendingBindings()
    }


    private fun setName(character: RickAndMortyCharacter) {
        binding.nameTextView.text = character.name
    }

    private fun setStatus(character: RickAndMortyCharacter) {
        binding.statusTextView.text = character.status
        when(character.status) {
            Statuses.ALIVE.string -> {
                binding.statusTextView.setTextColor(Color.GREEN)
            }
            Statuses.DEAD.string -> {
                binding.statusTextView.setTextColor(Color.RED)
            }
            else -> {
                binding.statusTextView.setTextColor(Color.BLACK)
            }
        }
    }

    fun bind(character: RickAndMortyCharacter) {
        binding.character = character
        setStatus(character)
        setName(character)
        binding.invalidateAll()
        binding.executePendingBindings()
    }

    companion object {

        fun getViewHolderFrom(parent: ViewGroup, viewModel: ListViewModel): RickAndMortyCharacterViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = DataBindingUtil.inflate<FragmentListItemBinding>(inflater, R.layout.fragment_list_item, parent, false)
            return RickAndMortyCharacterViewHolder(viewModel, binding, RickAndMortyCharacter())
        }

    }

    enum class Statuses(val string: String) {
        ALIVE("Alive"), DEAD("Dead"), UNKNOWN("Unknown")
    }

}