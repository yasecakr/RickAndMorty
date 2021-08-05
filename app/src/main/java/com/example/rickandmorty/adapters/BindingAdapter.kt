package com.example.rickandmorty.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.rickandmorty.R
import com.example.rickandmorty.model.Character
import com.squareup.picasso.Picasso

@BindingAdapter("submitNewList")
fun setRecyclerViewItem(recyclerView: RecyclerView, items: List<Any>?) {

    if (items != null) {
        if (items.isNotEmpty()) {

            items.let {
                when (it.first()) {
                    is Character -> {
                        val adapter: CharacterAdapter = recyclerView.adapter as CharacterAdapter
                        adapter.submitList(items as List<Character>)
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
                    .placeholder(R.drawable.ic_launcher_foreground)
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