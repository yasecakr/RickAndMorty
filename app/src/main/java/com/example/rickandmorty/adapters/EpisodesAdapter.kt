package com.example.rickandmorty.adapters

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.CharacterItemBinding
import com.example.rickandmorty.databinding.EpisodesItemBinding
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.model.Episode

class EpisodesAdapter(private val clickListener:EpisodeClickListener): androidx.recyclerview.widget.ListAdapter<Episode, EpisodesAdapter.EpisodeViewHolder>(
    EpisodeDiffCallbacks()) {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EpisodeViewHolder {
        return EpisodeViewHolder.from(parent)
    }
    class EpisodeViewHolder(private val itemBinding: EpisodesItemBinding): RecyclerView.ViewHolder(itemBinding.root){
        fun bind(data: Episode, clickListener: EpisodeClickListener){
            itemBinding.listItemEpisode =data
            itemBinding.episodeClickListener = clickListener
            itemBinding.executePendingBindings()

        }
        companion object{
            fun from(parent: ViewGroup):EpisodeViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = EpisodesItemBinding.inflate(layoutInflater, parent, false)
                return EpisodeViewHolder(binding)
            }
        }
    }


    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val item =getItem(position)
        item?.let {holder.bind(item, clickListener)  }

    }

}

class EpisodeDiffCallbacks : DiffUtil.ItemCallback<Episode>() {
    override fun areItemsTheSame(oldItem: Episode, newItem: Episode): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Episode, newItem: Episode): Boolean {
        return oldItem.id == newItem.id
    }
}
class EpisodeClickListener()