package br.com.lira.rickandmorty.features.characterslist.presentation.view

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import br.com.lira.rickandmorty.R
import br.com.lira.rickandmorty.features.characterdetails.presentation.view.CharacterDetailsFragment
import br.com.lira.rickandmorty.features.characterslist.presentation.view.adapter.CharactersAdapter
import br.com.lira.rickandmorty.features.characterslist.presentation.view.adapter.CharactersLoadStateAdapter
import br.com.lira.rickandmorty.features.characterslist.presentation.viewmodel.CharactersViewAction
import br.com.lira.rickandmorty.features.characterslist.presentation.viewmodel.CharactersViewModel
import br.com.lira.rickandmorty.core.toolkit.navigateToFragment
import br.com.lira.rickandmorty.core.toolkit.runWhenInteracted
import br.com.lira.rickandmorty.core.toolkit.setFocusWithKeyboard
import br.com.lira.rickandmorty.databinding.FragmentCharactersBinding
import br.com.lira.rickandmorty.main.presentation.CommonToolbarHandler
import br.com.lira.rickandmorty.main.presentation.DefaultToolbarHandler
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment(), CommonToolbarHandler by DefaultToolbarHandler {

    private lateinit var binding: FragmentCharactersBinding
    private val viewModel: CharactersViewModel by viewModels()

    private lateinit var charactersAdapter: CharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharactersBinding.inflate(inflater).apply {
            lifecycleOwner = this@CharactersFragment
            toolbarHandler = this@CharactersFragment
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
        charactersAdapter = CharactersAdapter(viewModel)
        binding.recyclerView.adapter = charactersAdapter.apply {
            withLoadStateFooter(
                footer = CharactersLoadStateAdapter()
            )
            addOnPagesUpdatedListener { viewModel.onCharactersListSubmitted() }
        }
        binding.searchView.search.setOnFocusChangeListener { _, hasFocus ->
            binding.searchView.search.setFocusWithKeyboard(hasFocus)
            viewModel.onSearchFocusChanged(hasFocus)
        }
    }

    private fun observeViewState() {
        viewModel.viewState.characters.observe(viewLifecycleOwner) {
            charactersAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
        viewModel.viewState.action.observe(viewLifecycleOwner) { action ->
            when (action) {
                is CharactersViewAction.OpenCharacterDetails -> openCharactersScreen(
                    action.characterId
                )
            }
        }
    }

    override fun onSearchClicked() {
        viewModel.onSearchClicked()
        focusOnSearch()
    }

    private fun focusOnSearch() {
        binding.searchView.search.setFocusWithKeyboard(true)
        binding.recyclerView.scrollToPosition(0)
        binding.recyclerView.runWhenInteracted {
            viewModel.onSearchFocusChanged(false)
        }
    }

    private fun openCharactersScreen(characterId: Long) {
        navigateToFragment(
            R.id.app_nav_host_fragment,
            CharacterDetailsFragment.newInstance(characterId)
        )
    }
}