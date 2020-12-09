package com.example.rickandmorty.ui

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.rickandmorty.R


@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        val imgUri = imageUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context).apply {
            RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.connection_error_image)
        }
                .load(imgUri)
                .into(imageView)
    }
}