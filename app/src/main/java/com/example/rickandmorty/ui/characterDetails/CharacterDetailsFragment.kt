package com.example.rickandmorty.ui.characterDetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.CharacterDetailsFragmentBinding
import com.example.rickandmorty.data.model.Episode
import com.example.rickandmorty.repository.CharacterDetailsRepository

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
        viewModel.episodesReady.observe(viewLifecycleOwner, {
            when(it) {
                true ->binding.progressBar.visibility= GONE
                false -> binding.progressBar.visibility= VISIBLE
            }
        })

        viewModel.showEpisodes.observe(viewLifecycleOwner, {
            when(it){
                true->showEpisodesLayout()
                false-> unShowEpisodesLayout()
            }
        })

        binding.closeImageButton.setOnClickListener {
            navigateToCharacters()
        }

        binding.episodeImageButton.setOnClickListener {
            viewModel.episodesIsShow()
        }

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
    private fun navigateToCharacters(){
        findNavController().navigate(CharacterDetailsFragmentDirections.actionCharacterDetailsFragmentToCharactersFragment())
    }
    private fun unShowEpisodesLayout(){
        binding.episodeImageButton.setImageResource(R.drawable.resources_nav_bar_back)
        binding.episodesLayout.visibility = GONE

    }
    private fun showEpisodesLayout(){
        binding.episodeImageButton.setImageResource(R.drawable.resources_nav_bar_up)
        binding.episodesLayout.visibility = VISIBLE
    }

}