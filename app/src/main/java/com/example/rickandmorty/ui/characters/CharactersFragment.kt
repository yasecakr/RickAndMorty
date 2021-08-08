package com.example.rickandmorty.ui.characters

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.adapters.CharacterAdapter
import com.example.rickandmorty.adapters.CharactersClickListener
import com.example.rickandmorty.databinding.CharactersFragmentBinding
import com.example.rickandmorty.data.model.Character
import com.example.rickandmorty.repository.CharactersRepository
import kotlinx.coroutines.flow.collectLatest

class CharactersFragment : Fragment() {

    companion object {
        fun newInstance() = CharactersFragment()
    }

    private lateinit var viewModel: CharactersViewModel
    private lateinit var binding: CharactersFragmentBinding
    private lateinit var charactersRepository: CharactersRepository
    private lateinit var characterRecycler:RecyclerView
    private lateinit var recyclerAdapter: CharacterAdapter

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

        initRecycler()



    }

    private fun initRecycler() {
        binding.rcyChar.apply {
            val manager= GridLayoutManager(activity, 2)
            val decoration = DividerItemDecoration(activity?.applicationContext,DividerItemDecoration.VERTICAL)
            layoutManager= manager
            addItemDecoration(decoration)
            recyclerAdapter= CharacterAdapter(CharactersClickListener { character ->
                navigateToCharacterDetails(character)
            })
            adapter=recyclerAdapter
            lifecycleScope.launchWhenCreated {
                viewModel.getPagingListData().collectLatest {
                    recyclerAdapter.submitData(it)
                }
            }
        }
    }


    private fun init(){
        charactersRepository = CharactersRepository()
        viewModel = ViewModelProvider(this, CharactersViewModel.Factory(charactersRepository))
            .get(CharactersViewModel::class.java)
        binding.viewModel= viewModel
        binding.lifecycleOwner= this
    }

    private fun navigateToCharacterDetails(character: Character){
        findNavController().navigate(CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailsFragment(charaterId = character.id))

  }

}