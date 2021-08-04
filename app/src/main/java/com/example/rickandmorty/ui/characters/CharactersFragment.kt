package com.example.rickandmorty.ui.characters

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.CharactersFragmentBinding
import com.example.rickandmorty.repository.CharactersRepository
import com.example.rickandmorty.viewModel.CharactersViewModel

class CharactersFragment : Fragment() {

    companion object {
        fun newInstance() = CharactersFragment()
    }

    private lateinit var viewModel: CharactersViewModel
    private lateinit var binding: CharactersFragmentBinding
    private lateinit var charactersRepository: CharactersRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CharactersFragmentBinding.inflate(inflater)
        init()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
    private fun init(){
        charactersRepository = CharactersRepository()
        viewModel = ViewModelProvider(this,
        CharactersViewModel.Factory(charactersRepository)).get(CharactersViewModel::class.java)
        binding.viewModel= viewModel
        binding.lifecycleOwner= this
    }

}