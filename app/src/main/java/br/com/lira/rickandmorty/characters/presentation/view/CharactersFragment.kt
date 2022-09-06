package br.com.lira.rickandmorty.characters.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.lira.rickandmorty.characters.presentation.view.adapter.CharactersAdapter
import br.com.lira.rickandmorty.characters.presentation.viewmodel.CharactersViewModel
import br.com.lira.rickandmorty.databinding.FragmentCharactersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private lateinit var binding: FragmentCharactersBinding
    private val viewModel: CharactersViewModel by viewModels()

    private lateinit var charactersAdapter: CharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharactersBinding.inflate(inflater).apply {
            lifecycleOwner = this@CharactersFragment
            viewState = viewModel.viewState
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        observeViewState()
        viewModel.init()
    }

    private fun setupViews() {
        charactersAdapter = CharactersAdapter()
        binding.recyclerView.adapter = charactersAdapter
    }

    private fun observeViewState() {
        viewModel.viewState.characters.observe(viewLifecycleOwner) {
            charactersAdapter.submitList(it)
        }
    }
}