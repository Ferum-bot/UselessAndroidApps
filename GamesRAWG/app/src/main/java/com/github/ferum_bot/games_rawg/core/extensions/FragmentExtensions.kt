package com.github.ferum_bot.games_rawg.core.extensions

import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.github.ferum_bot.games_rawg.ui.fragment.base.FragmentBindingDelegate

/**
 * Created by Matvey Popov.
 * Date: 11.02.2021
 * Time: 21:46
 * Project: Games-RAWG
 */

fun <T: ViewBinding> Fragment.viewBinding(viewBindingFactory: (View) -> T) =
    FragmentBindingDelegate(this, viewBindingFactory)