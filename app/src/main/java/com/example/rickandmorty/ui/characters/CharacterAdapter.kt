package com.example.rickandmorty.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.CharacterItemBinding
import com.example.rickandmorty.data.model.Character

class CharacterAdapter(private val clickListener:CharactersClickListener): PagingDataAdapter<Character, CharacterAdapter.CharacterViewHolder>(
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
        item?.let {holder.bind(item, clickListener)  }

    }
}

class CharacterDiffCallbacks : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }
}
class CharactersClickListener(
    val detailsClick:(character: Character)-> Unit
){
    fun navigateToCharacterDetails(character: Character)= detailsClick(character)
}