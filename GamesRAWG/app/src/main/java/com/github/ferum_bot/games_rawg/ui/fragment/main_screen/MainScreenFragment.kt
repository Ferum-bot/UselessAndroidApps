package com.github.ferum_bot.games_rawg.ui.fragment.main_screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.ferum_bot.games_rawg.R
import com.github.ferum_bot.games_rawg.core.extensions.viewBinding
import com.github.ferum_bot.games_rawg.databinding.FragmentMainBinding
import com.github.ferum_bot.games_rawg.di.components.DaggerMainScreenComponent
import com.github.ferum_bot.games_rawg.di.components.MainScreenComponent
import com.github.ferum_bot.games_rawg.viewmodels.main_screen.MainScreenViewModel

/**
 * Created by Matvey Popov.
 * Date: 08.02.2021
 * Time: 23:49
 * Project: Games-RAWG
 */
class MainScreenFragment: Fragment(R.layout.fragment_main) {
    private val component by lazy { DaggerMainScreenComponent.builder().build() }
    private val viewModel by viewModels<MainScreenViewModel> { component.viewModelFactory() }
    private val binding by viewBinding { FragmentMainBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAllObservers()
    }

    private fun setAllObservers() {
        viewModel.errorMessage.observe(viewLifecycleOwner) { message ->
            if (message != null) {
                showErrorMessage(message)
                viewModel.errorMessageHasShown()
            }
        }

        viewModel.rated.observe(viewLifecycleOwner) { pagingData ->

        }

        viewModel.latestReleases.observe(viewLifecycleOwner) { pagingData ->

        }

        viewModel.mostAnticipated.observe(viewLifecycleOwner) {pagingData ->

        }
    }

    private fun showErrorMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}