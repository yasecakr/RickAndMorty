package com.example.rickandmorty.ui.characterDetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rickandmorty.databinding.CharacterDetailsFragmentBinding
import com.example.rickandmorty.repository.CharacterDetailsRepository
import com.example.rickandmorty.viewModel.CharacterDetailsViewModel

class CharacterDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = CharacterDetailsFragment()
    }

    private lateinit var viewModel: CharacterDetailsViewModel
    private lateinit var binding: CharacterDetailsFragmentBinding
    private lateinit var characterDetailsRepository: CharacterDetailsRepository

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
        viewModel.character.observe(viewLifecycleOwner,{
            binding.characterDecsTextView.text = viewModel.getCharacterDescription()

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

}