package com.github.ferum_bot.start_app.ui.recycler_view

import android.view.View
import com.github.ferum_bot.start_app.core.models.BaseListItem
import com.github.ferum_bot.start_app.core.models.HorizontalListItem
import com.github.ferum_bot.start_app.core.models.HorizontalRecyclerItem
import com.github.ferum_bot.start_app.databinding.FragmentHorizontalListItemBinding
import com.github.ferum_bot.start_app.databinding.FragmentHorizontalRecyclerItemBinding
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

/**
 * Created by Matvey Popov.
 * Date: 27.12.2020
 * Time: 22:28
 * Project: StartApp
 */
object MainScreenDelegates {

//    private val horizontalListItemDelegate = adapterDelegateViewBinding<HorizontalListItem, BaseListItem, FragmentHorizontalListItemBinding>(
//        {inflater, container -> FragmentHorizontalListItemBinding.inflate(inflater, container, false)}
//    ) {
//        bind {
//            binding.textView.text = item.title
//            binding.imageView.setImageDrawable(item.drawableIcon)
//            binding.executePendingBindings()
//        }
//    }

    private fun horizontalListItemDelegate(itemClickListener: (HorizontalListItem) -> Unit) = adapterDelegateViewBinding<HorizontalListItem, BaseListItem, FragmentHorizontalListItemBinding>(
            {inflater, container -> FragmentHorizontalListItemBinding.inflate(inflater, container, false)}
    ) {
        bind {
            binding.materialCard.setOnClickListener{itemClickListener(item)}
            binding.textView.text = item.title
            binding.imageView.setImageDrawable(item.drawableIcon)
            binding.executePendingBindings()
        }
    }

    fun firstLetterActivitiesDelegate(itemClickListener: (HorizontalListItem) -> Unit) = adapterDelegateViewBinding<HorizontalRecyclerItem, BaseListItem, FragmentHorizontalRecyclerItemBinding>(
            { inflater, container -> FragmentHorizontalRecyclerItemBinding.inflate(inflater, container, false).apply {
                horizontalRecyclerView.adapter = ListDelegationAdapter(
                        horizontalListItemDelegate(itemClickListener)
                )
            }}
    ) {

        bind {
            binding.title.text = item.firstLetter
            (binding.horizontalRecyclerView.adapter as ListDelegationAdapter<List<BaseListItem>>).apply {
                items = item.listOfItems
                notifyDataSetChanged()
            }
            binding.executePendingBindings()
        }
    }

//    val firstLetterActivitiesDelegate = adapterDelegateViewBinding<HorizontalRecyclerItem, BaseListItem, FragmentHorizontalRecyclerItemBinding>(
//        { inflater, container -> FragmentHorizontalRecyclerItemBinding.inflate(inflater, container, false).apply {
//            horizontalRecyclerView.adapter = ListDelegationAdapter(
//                    horizontalListItemDelegate
//            )
//        }}
//    ) {
//
//        bind {
//            binding.title.text = item.firstLetter
//            (binding.horizontalRecyclerView.adapter as ListDelegationAdapter<List<BaseListItem>>).apply {
//                items = item.listOfItems
//                notifyDataSetChanged()
//            }
//            binding.executePendingBindings()
//        }
//    }

}