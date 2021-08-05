package com.example.rickandmorty.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.CharacterItemBinding
import com.example.rickandmorty.model.Character

class CharacterAdapter(private val clickListener:CharactersClickListener): ListAdapter<Character, CharacterAdapter.CharacterViewHolder>(
    CharacterDiffCallbacks()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterViewHolder {
        return CharacterViewHolder.from(parent)
    }
    class CharacterViewHolder(private val itemBinding: CharacterItemBinding):RecyclerView.ViewHolder(itemBinding.root){
        fun bind(data: Character, clickListener: CharactersClickListener){
            itemBinding.listItemCharacter =data
            itemBinding.clickListener = clickListener
            itemBinding.executePendingBindings()
        }
        companion object{
            fun from(parent: ViewGroup):CharacterViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CharacterItemBinding.inflate(layoutInflater, parent, false)
                return CharacterViewHolder(binding)
            }
        }
    }


    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item =getItem(position)
        holder.bind(item, clickListener)
    }
}

class CharacterDiffCallbacks : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}
class CharactersClickListener()