package com.github.ferum_bot.games_rawg.ui.fragment.main_screen

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import com.github.ferum_bot.games_rawg.R
import com.github.ferum_bot.games_rawg.core.Variables
import com.github.ferum_bot.games_rawg.core.extensions.viewBinding
import com.github.ferum_bot.games_rawg.core.models.*
import com.github.ferum_bot.games_rawg.databinding.FragmentMainBinding
import com.github.ferum_bot.games_rawg.di.DI
import com.github.ferum_bot.games_rawg.ui.recycler_view.delegate_adapter.adapters.MainScreenAdapter
import com.github.ferum_bot.games_rawg.ui.recycler_view.paging.adapters.GameThinLoadStatePagingAdapter
import com.github.ferum_bot.games_rawg.ui.recycler_view.paging.adapters.GameWideLoadStatePagingAdapter
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

    private lateinit var latestReleasesList: HorizontalGameWideListItem
    private lateinit var mostAnticipatedList: HorizontalGameThinListItem
    private lateinit var ratedList: HorizontalGameWideListItem

    private lateinit var racingGenreList: HorizontalGameWideListItem
    private lateinit var shooterGenreList: HorizontalGameWideListItem
    private lateinit var adventureGenreList: HorizontalGameWideListItem
    private lateinit var actionGenreList: HorizontalGameWideListItem
    private lateinit var rpgGenreList: HorizontalGameWideListItem
    private lateinit var fightingGenreList: HorizontalGameWideListItem
    private lateinit var puzzleGenreList: HorizontalGameWideListItem
    private lateinit var strategyGenreList: HorizontalGameWideListItem

    private val mainListAdapter = MainScreenAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAllHorizontalLists()
        setAllObservers()
        setAllClickListeners()
        initMainRecyclerView()
        doInitialSetUp()
    }

    private fun initAllHorizontalLists() {
        latestReleasesList =
            HorizontalGameListItemBuilder.provideWideHorizontalListItemWithTitle(
                getString(R.string.latest_releases),
                this::processErrorMessage
            )

        mostAnticipatedList =
            HorizontalGameListItemBuilder.provideThinHorizontalListItemWithTitle(
                getString(R.string.most_anticipated),
                this::processErrorMessage
            )

        ratedList =
            HorizontalGameListItemBuilder.provideWideHorizontalListItemWithTitle(
                getString(R.string.most_rated_in_2020),
                this::processErrorMessage
            )

        racingGenreList =
            HorizontalGameListItemBuilder.provideWideHorizontalListItemWithTitle(
                getString(R.string.racing_genre_title),
                this::processErrorMessage
            )

        shooterGenreList =
            HorizontalGameListItemBuilder.provideWideHorizontalListItemWithTitle(
                getString(R.string.shooter_genre_title),
                this::processErrorMessage
            )

        adventureGenreList =
            HorizontalGameListItemBuilder.provideWideHorizontalListItemWithTitle(
                getString(R.string.adventure_genre_title),
                this::processErrorMessage
            )

        actionGenreList =
            HorizontalGameListItemBuilder.provideWideHorizontalListItemWithTitle(
                getString(R.string.action_genre_title),
                this::processErrorMessage
            )

        rpgGenreList =
            HorizontalGameListItemBuilder.provideWideHorizontalListItemWithTitle(
                getString(R.string.rpg_genre_title),
                this::processErrorMessage
            )

        fightingGenreList =
            HorizontalGameListItemBuilder.provideWideHorizontalListItemWithTitle(
                getString(R.string.fighting_genre_title),
                this::processErrorMessage
            )

        puzzleGenreList =
            HorizontalGameListItemBuilder.provideWideHorizontalListItemWithTitle(
                getString(R.string.puzzle_genre_title),
                this::processErrorMessage
            )

        strategyGenreList =
            HorizontalGameListItemBuilder.provideWideHorizontalListItemWithTitle(
                getString(R.string.strategy_genre_title),
                this::processErrorMessage
            )
    }

    private fun setAllObservers() {
        viewModel.errorMessage.observe(viewLifecycleOwner) { message ->
            if (message != null) {
                processErrorMessage(message)
                viewModel.errorMessageHasShown()
            }
        }

        viewModel.rated.observe(viewLifecycleOwner) { pagingData ->
            ratedList.adapter.submitData(provideLifecycle(), pagingData)
        }

        viewModel.latestReleases.observe(viewLifecycleOwner) { pagingData ->
            latestReleasesList.adapter.submitData(provideLifecycle(), pagingData)
        }

        viewModel.mostAnticipated.observe(viewLifecycleOwner) {pagingData ->
            mostAnticipatedList.adapter.submitData(provideLifecycle(), pagingData)
        }

        viewModel.actionGenre.observe(viewLifecycleOwner) { pagingDate ->
            actionGenreList.adapter.submitData(provideLifecycle(), pagingDate)
        }

        viewModel.racingGenre.observe(viewLifecycleOwner) { pagingDate ->
            racingGenreList.adapter.submitData(provideLifecycle(), pagingDate)
        }

        viewModel.shooterGenre.observe(viewLifecycleOwner) { pagingDate ->
            shooterGenreList.adapter.submitData(provideLifecycle(), pagingDate)
        }

        viewModel.adventureGenre.observe(viewLifecycleOwner) { pagingDate ->
            adventureGenreList.adapter.submitData(provideLifecycle(), pagingDate)
        }

        viewModel.rpgGenre.observe(viewLifecycleOwner) { pagingDate ->
            rpgGenreList.adapter.submitData(provideLifecycle(), pagingDate)
        }

        viewModel.fightingGenre.observe(viewLifecycleOwner) { pagingDate ->
            fightingGenreList.adapter.submitData(provideLifecycle(), pagingDate)
        }

        viewModel.puzzleGenre.observe(viewLifecycleOwner) { pagingDate ->
            puzzleGenreList.adapter.submitData(provideLifecycle(), pagingDate)
        }

        viewModel.strategyGenre.observe(viewLifecycleOwner) { pagingDate ->
            strategyGenreList.adapter.submitData(provideLifecycle(), pagingDate)
        }
    }

    private fun setAllClickListeners() {
        binding.retryButton.setOnClickListener {
            if (networkConnectionIsNotAvailable()) {
                showErrorMessage(R.string.no_internet_connection)
                return@setOnClickListener
            }
            refreshAllAdapters()
        }
    }

    private fun refreshAllAdapters() {
        showRecycler()
        ratedList.adapter.refresh()
        latestReleasesList.adapter.refresh()
        mostAnticipatedList.adapter.refresh()
        racingGenreList.adapter.refresh()
        shooterGenreList.adapter.refresh()
        adventureGenreList.adapter.refresh()
        actionGenreList.adapter.refresh()
        rpgGenreList.adapter.refresh()
        fightingGenreList.adapter.refresh()
        puzzleGenreList.adapter.refresh()
        strategyGenreList.adapter.refresh()
    }

    private fun showRecycler() {
        binding.recyclerView.visibility = View.VISIBLE
        binding.retryButton.visibility = View.GONE
        binding.errorImageView.visibility = View.GONE
    }

    private fun initMainRecyclerView() {
        binding.recyclerView.adapter = mainListAdapter
        mainListAdapter.items = listOf(
            latestReleasesList,
            mostAnticipatedList,
            ratedList,
            racingGenreList,
            shooterGenreList,
            adventureGenreList,
            actionGenreList,
            rpgGenreList,
            fightingGenreList,
            puzzleGenreList,
            strategyGenreList
        )
    }

    private fun doInitialSetUp() {
        if (networkConnectionIsNotAvailable()) {
            showErrorImage()
            showErrorMessage(R.string.no_internet_connection)
        }
    }

    private fun provideLifecycle() =
        viewLifecycleOwner.lifecycle

    private fun processErrorMessage(message: String) {
        if (networkConnectionIsNotAvailable()) {
            showErrorMessage(R.string.no_internet_connection)
        }
        else {
            showErrorMessage(message)
        }
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
        binding.recyclerView.visibility = View.GONE
        binding.errorImageView.visibility = View.VISIBLE
        binding.retryButton.visibility = View.VISIBLE
    }
}