package com.github.ferum_bot.games_rawg.core.extensions

import android.content.res.Resources
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.DrawableTransformation
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.github.ferum_bot.games_rawg.R
import com.github.ferum_bot.games_rawg.core.enums.ViewHoldersTypes

/**
 * Created by Matvey Popov.
 * Date: 11.02.2021
 * Time: 21:59
 * Project: Games-RAWG
 */

fun loadImageWithDefaultOptions(view: View, whereToDownLoad: ImageView, imageUrl: String?, type: ViewHoldersTypes = ViewHoldersTypes.THIN) {
    val resources = view.resources
    Glide.with(view)
        .applyDefaultRequestOptions(getDefaultGlideOptions())
        .load(imageUrl)
        .override(
            getGameCardWidth(type, resources),
            getGameCardHeight(type, resources)
        )
        .transform(
            CenterCrop(),
            RoundedCorners(getDefaultCornersSize(resources))
        )
        .transition(getDefaultCrossFade())
        .into(whereToDownLoad)
}

private fun getGameCardWidth(type: ViewHoldersTypes, resources: Resources): Int {
    return when(type) {
        ViewHoldersTypes.THIN -> {
            resources.getDimensionPixelOffset(R.dimen.game_card_thin_width)
        }
        ViewHoldersTypes.WIDE -> {
            resources.getDimensionPixelOffset(R.dimen.game_card_wide_width)
        }
    }
}

private fun getGameCardHeight(type: ViewHoldersTypes, resources: Resources): Int {
    return when(type) {
        ViewHoldersTypes.THIN -> {
            resources.getDimensionPixelOffset(R.dimen.game_card_thin_height)
        }
        ViewHoldersTypes.WIDE -> {
            resources.getDimensionPixelOffset(R.dimen.game_card_wide_height)
        }
    }
}

private fun getDefaultCornersSize(resources: Resources): Int =
    resources.getDimensionPixelOffset(R.dimen.game_card_radius)

private fun getDefaultCrossFade(): DrawableTransitionOptions =
    withCrossFade().crossFade(1000)

private fun getDefaultGlideOptions(): RequestOptions =
    RequestOptions()
        .placeholder(R.drawable.bg_item_placeholder)
        .error(R.drawable.error_image_for_glide)
        .diskCacheStrategy(DiskCacheStrategy.ALL)