package com.github.ferum_bot.start_app.ui.fragments.main_list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.ferum_bot.start_app.R
import com.github.ferum_bot.start_app.core.models.BaseListItem
import com.github.ferum_bot.start_app.core.models.HorizontalListItem
import com.github.ferum_bot.start_app.core.models.HorizontalRecyclerItem
import com.github.ferum_bot.start_app.core.states.ActivitiesLoadingStates
import com.github.ferum_bot.start_app.databinding.FragmentMainListBinding
import com.github.ferum_bot.start_app.ui.recycler_view.MainScreenDelegates
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

/**
 * Created by Matvey Popov.
 * Date: 27.12.2020
 * Time: 20:22
 * Project: StartApp
 */
class MainListFragment: Fragment() {

    private lateinit var binding: FragmentMainListBinding

    private lateinit var viewModel: MainListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_list, container, false)

        val factory  = MainListViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(MainListViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAllObservers()
        setAllAdapters()

        val packageManager = requireActivity().packageManager
        viewModel.getAllActivities(packageManager)
    }

    private fun setAllObservers() {

        viewModel.status.observe(viewLifecycleOwner, Observer { newStatus ->
            when(newStatus) {
                ActivitiesLoadingStates.NOT_ACTIVE -> {
                    hideRecyclerView()
                }
                ActivitiesLoadingStates.DONE -> {
                    showRecyclerView()
                }
                ActivitiesLoadingStates.ERROR -> {
                    showErrorImage()
                }
                ActivitiesLoadingStates.LOADING -> {
                    showLoadingAnimation()
                }
            }
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer { newMessage ->
            if (newMessage != null) {
                showErrorMessage(newMessage)
                viewModel.errorMessageHasShown()
            }
        })

        viewModel.listOfHorizontalRecyclerItem.observe(viewLifecycleOwner, Observer { newList ->
            if (newList != null) {
                (binding.mainRecyclerView.adapter as ListDelegationAdapter<List<BaseListItem>>).items = newList
                (binding.mainRecyclerView.adapter as ListDelegationAdapter<List<BaseListItem>>).notifyDataSetChanged()
            }
        })
    }

    private fun setAllAdapters() {

        val adapter = ListDelegationAdapter(
            MainScreenDelegates.firstLetterActivitiesDelegate(
                    fun(item: HorizontalListItem) {
                        val packageName = viewModel.getPackageName(item.id)
                        val name = viewModel.getName(item.id)
                        val intent = Intent(Intent.ACTION_MAIN).apply {
                            setClassName(packageName, name)
                            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        }
                        val context = requireContext()
                        context.startActivity(intent)
                    }
            )
        )

        with(binding) {
            mainRecyclerView.adapter = adapter
            binding.executePendingBindings()
        }
    }

    private fun showErrorMessage(message: String) {
        val result = "Something went wrong: $message"
        Toast.makeText(requireContext(), result, Toast.LENGTH_SHORT).show()
    }

    private fun hideRecyclerView() {
        binding.mainRecyclerView.visibility = View.GONE
        binding.imageView.visibility = View.INVISIBLE
        binding.executePendingBindings()
    }

    private fun showLoadingAnimation() {
        binding.mainRecyclerView.visibility = View.GONE
        binding.imageView.setImageResource(R.drawable.loading_animation)
        binding.imageView.visibility = View.VISIBLE
        binding.executePendingBindings()
    }

    private fun showRecyclerView() {
        binding.mainRecyclerView.visibility = View.VISIBLE
        binding.imageView.visibility = View.GONE
        binding.executePendingBindings()
    }

    private fun showErrorImage() {
        binding.mainRecyclerView.visibility = View.GONE
        binding.imageView.setImageResource(R.drawable.error_image)
        binding.imageView.visibility = View.VISIBLE
        binding.executePendingBindings()
    }

}