package br.com.lira.rickandmorty.characters.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.lira.rickandmorty.R
import br.com.lira.rickandmorty.characterdetails.presentation.view.CharacterDetailsFragment
import br.com.lira.rickandmorty.characters.presentation.view.adapter.CharactersAdapter
import br.com.lira.rickandmorty.characters.presentation.view.adapter.CharactersLoadStateAdapter
import br.com.lira.rickandmorty.characters.presentation.viewmodel.CharactersViewModel
import br.com.lira.rickandmorty.core.toolkit.navigateToFragment
import br.com.lira.rickandmorty.databinding.FragmentCharactersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment(), CharactersListener {

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
    }

    private fun setupViews() {
        charactersAdapter = CharactersAdapter(this)
        binding.recyclerView.adapter = charactersAdapter.withLoadStateFooter(
            footer = CharactersLoadStateAdapter {  }
        )
    }

    private fun observeViewState() {
        viewModel.viewState.characters.observe(viewLifecycleOwner) {
            charactersAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    override fun onCharacterClicked(characterId: Long) {
        navigateToFragment(
            R.id.app_nav_host_fragment,
            CharacterDetailsFragment.newInstance(characterId)
        )
    }
}