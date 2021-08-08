package com.example.rickandmorty.ui.characterDetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.rickandmorty.adapters.EpisodeClickListener
import com.example.rickandmorty.adapters.EpisodesAdapter
import com.example.rickandmorty.databinding.CharacterDetailsFragmentBinding
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.model.Episode
import com.example.rickandmorty.repository.CharacterDetailsRepository
import com.example.rickandmorty.viewModel.CharacterDetailsViewModel

class CharacterDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = CharacterDetailsFragment()
    }

    private lateinit var viewModel: CharacterDetailsViewModel
    private lateinit var binding: CharacterDetailsFragmentBinding
    private lateinit var characterDetailsRepository: CharacterDetailsRepository
    private lateinit var recyclerAdapterEpisodes: EpisodesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= CharacterDetailsFragmentBinding.inflate(inflater)
        init()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.recyclerEpisodes.adapter=EpisodesAdapter(EpisodeClickListener())

        viewModel.character.observe(viewLifecycleOwner,{
            binding.characterDecsTextView.text = viewModel.getCharacterDescription()
            lifecycleScope.launchWhenCreated {
                viewModel.getAllEpisodes()
                submitListRecyclerrr(viewModel.episodes.value!!)
            }

        })

    }
    private fun init(){
        val arguments =CharacterDetailsFragmentArgs.fromBundle(requireArguments())
        characterDetailsRepository= CharacterDetailsRepository()
        viewModel = ViewModelProvider(this, CharacterDetailsViewModel.Factory(arguments.charaterId,characterDetailsRepository))
            .get(CharacterDetailsViewModel::class.java)
        binding.viewModel=viewModel
        binding.lifecycleOwner= this
    }
    private fun submitListRecyclerrr(episodes: ArrayList<Episode>){
        binding.recyclerEpisodes.apply {
            recyclerAdapterEpisodes= EpisodesAdapter(EpisodeClickListener())
            adapter= recyclerAdapterEpisodes
            recyclerAdapterEpisodes.submitList(episodes)


        }
    }

}