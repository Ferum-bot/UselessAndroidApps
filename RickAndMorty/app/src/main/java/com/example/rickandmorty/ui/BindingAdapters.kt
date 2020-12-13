package com.example.rickandmorty.ui

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.rickandmorty.R


@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        val options = RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.connection_error_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
        val imgUri = imageUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context)
                .applyDefaultRequestOptions(options)
                .load(imgUri)
                .into(imageView)
    }
}