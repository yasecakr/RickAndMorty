package com.example.rickandmorty.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.rickandmorty.R
import com.example.rickandmorty.data.model.Episode
import com.example.rickandmorty.ui.characterDetails.EpisodesAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("submitNewList")
fun setRecyclerViewItem(recyclerView: RecyclerView, items: List<Any>?) {

    if (items != null) {
        if (items.isNotEmpty()) {

            items.let {
                when (it.first()) {
                    is Episode -> {
                        val adapter: EpisodesAdapter = recyclerView.adapter as EpisodesAdapter
                        adapter.submitList(items as List<Episode>)
                    }
                }
            }
        }
    }
}
@BindingAdapter("imgUrl")
fun setImage(imageView: ImageView, url: String?) {
    url?.let {


        Glide.with(imageView.context)
            .load(url)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imageView)
    }
}
@BindingAdapter("imgUrlCircle")
fun setImageCircle(imageView: ImageView, url: String?) {
    url?.let {


        Glide.with(imageView.context)
            .load(url)
            .circleCrop()
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imageView)
    }
}
@BindingAdapter("imgUrlPicasso")
fun setImageWhitPicasso(imageView: ImageView, url: String?) {
    url?.let {


        Picasso.get().load(url)
            .error(R.drawable.ic_launcher_background)
            .into(imageView)
    }
}