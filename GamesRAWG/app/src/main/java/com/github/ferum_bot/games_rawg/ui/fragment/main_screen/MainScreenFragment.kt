package com.github.ferum_bot.games_rawg.ui.fragment.main_screen

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.github.ferum_bot.games_rawg.R
import com.github.ferum_bot.games_rawg.core.Variables
import com.github.ferum_bot.games_rawg.core.extensions.viewBinding
import com.github.ferum_bot.games_rawg.core.models.*
import com.github.ferum_bot.games_rawg.core.models.interfaces.ListItem
import com.github.ferum_bot.games_rawg.databinding.FragmentMainBinding
import com.github.ferum_bot.games_rawg.di.DI
import com.github.ferum_bot.games_rawg.di.components.DaggerMainScreenComponent
import com.github.ferum_bot.games_rawg.di.components.MainScreenComponent
import com.github.ferum_bot.games_rawg.ui.recycler_view.delegate_adapter.adapters.MainScreenAdapter
import com.github.ferum_bot.games_rawg.ui.recycler_view.paging.adapters.GameThinPagingAdapter
import com.github.ferum_bot.games_rawg.ui.recycler_view.paging.adapters.GameWidePagingAdapter
import com.github.ferum_bot.games_rawg.ui.recycler_view.paging.view_holders.PagingGameThinViewHolder
import com.github.ferum_bot.games_rawg.ui.recycler_view.paging.view_holders.PagingGameWideViewHolder
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

    private lateinit var latestReleasesList: HorizontalGameWideListItem
    private lateinit var mostAnticipatedList: HorizontalGameThinListItem
    private lateinit var ratedList: HorizontalGameWideListItem

    private val mainListAdapter = MainScreenAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAllHorizontalLists()
        setAllObservers()
        initRecyclerView()
    }

    private fun initAllHorizontalLists() {
        latestReleasesList =
            HorizontalGameListItemBuilder.provideWideHorizontalListItemWithTitle(getString(R.string.latest_releases))
        mostAnticipatedList =
            HorizontalGameListItemBuilder.provideThinHorizontalListItemWithTitle(getString(R.string.most_anticipated))
        ratedList =
            HorizontalGameListItemBuilder.provideWideHorizontalListItemWithTitle(getString(R.string.most_rated_in_2020))
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
            ratedList.adapter.submitData(lifecycle, pagingData)
        }

        viewModel.latestReleases.observe(viewLifecycleOwner) { pagingData ->
            Log.i("MainScreen", "latestReleases")
            latestReleasesList.adapter.submitData(lifecycle, pagingData)
            Log.i("MainScreen", "latestReleases: ${latestReleasesList.adapter.itemCount}")
        }

        viewModel.mostAnticipated.observe(viewLifecycleOwner) {pagingData ->
            Log.i("MainScreen", "mostAnticipated")
            mostAnticipatedList.adapter.submitData(lifecycle, pagingData)
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.adapter = mainListAdapter
        mainListAdapter.items = listOf(
            latestReleasesList,
            mostAnticipatedList,
            ratedList
        )
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