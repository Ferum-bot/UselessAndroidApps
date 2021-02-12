package com.github.ferum_bot.games_rawg.ui.fragment.main_screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.ferum_bot.games_rawg.R
import com.github.ferum_bot.games_rawg.core.Variables
import com.github.ferum_bot.games_rawg.core.extensions.viewBinding
import com.github.ferum_bot.games_rawg.core.models.HorizontalGameListItem
import com.github.ferum_bot.games_rawg.databinding.FragmentMainBinding
import com.github.ferum_bot.games_rawg.di.DI
import com.github.ferum_bot.games_rawg.di.components.DaggerMainScreenComponent
import com.github.ferum_bot.games_rawg.di.components.MainScreenComponent
import com.github.ferum_bot.games_rawg.ui.recycler_view.delegate_adapter.adapters.MainScreenAdapter
import com.github.ferum_bot.games_rawg.ui.recycler_view.paging.adapters.GameThinPagingAdapter
import com.github.ferum_bot.games_rawg.ui.recycler_view.paging.adapters.GameWidePagingAdapter
import com.github.ferum_bot.games_rawg.viewmodels.main_screen.MainScreenViewModel

/**
 * Created by Matvey Popov.
 * Date: 08.02.2021
 * Time: 23:49
 * Project: Games-RAWG
 */
class MainScreenFragment: Fragment(R.layout.fragment_main) {
    private val component by lazy { DI.mainScreenComponent }
    private val viewModel by viewModels<MainScreenViewModel> { component.viewModelFactory() }
    private val binding by viewBinding { FragmentMainBinding.bind(it) }



    private val latestReleasesAdapter = GameWidePagingAdapter()
    private val mostAnticipatedAdapter = GameThinPagingAdapter()
    private val ratedAdapter = GameWidePagingAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAllObservers()
        initRecyclerView()
    }

    private fun setAllObservers() {
        viewModel.errorMessage.observe(viewLifecycleOwner) { message ->
            if (message != null) {
                if (networkConnectionIsNotAvailable()) {
                    showErrorImage()
                    showErrorMessage(R.string.no_internet_connection)
                }
                else {
                    showErrorMessage(message)
                }
                viewModel.errorMessageHasShown()
            }
        }

        viewModel.rated.observe(viewLifecycleOwner) { pagingData ->
            ratedAdapter.submitData(lifecycle, pagingData)
        }

        viewModel.latestReleases.observe(viewLifecycleOwner) { pagingData ->
            latestReleasesAdapter.submitData(lifecycle, pagingData)
        }

        viewModel.mostAnticipated.observe(viewLifecycleOwner) {pagingData ->
            mostAnticipatedAdapter.submitData(lifecycle, pagingData)
        }
    }

    private fun initRecyclerView() {

    }

    private fun networkConnectionIsNotAvailable(): Boolean =
        !Variables.isNetworkConnectionAvailable

    private fun showErrorMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun showErrorMessage(@StringRes id: Int) {
        val message = getString(id)
        showErrorMessage(message)
    }

    private fun showErrorImage() {
        TODO("add drawable and error image")
    }
}