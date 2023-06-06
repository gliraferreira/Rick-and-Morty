package br.com.lira.rickandmorty.features.characterslist.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.lira.rickandmorty.R
import br.com.lira.rickandmorty.core.toolkit.navigateToFragment
import br.com.lira.rickandmorty.core.toolkit.runWhenInteracted
import br.com.lira.rickandmorty.core.toolkit.setFocusWithKeyboard
import br.com.lira.rickandmorty.databinding.FragmentCharactersBinding
import br.com.lira.rickandmorty.features.characterdetails.presentation.view.CharacterDetailsFragment
import br.com.lira.rickandmorty.features.characterslist.presentation.view.adapter.CharactersAdapter
import br.com.lira.rickandmorty.features.characterslist.presentation.view.adapter.CharactersLoadStateAdapter
import br.com.lira.rickandmorty.features.characterslist.presentation.viewmodel.CharactersViewAction
import br.com.lira.rickandmorty.features.characterslist.presentation.viewmodel.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val LIST_FIRST_POSITION = 0

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private lateinit var binding: FragmentCharactersBinding
    private val viewModel: CharactersViewModel by viewModels()

    private lateinit var charactersAdapter: CharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        setupToolbar()
        setupSearchView()
        setupRecyclerView()
        setupErrorView()
    }

    private fun setupToolbar() {
        binding.toolbarView.searchIcon.setOnClickListener {
            viewModel.onSearchClicked()
        }
    }

    private fun setupErrorView() {
        binding.errorState.btnTryAgain.setOnClickListener {
            viewModel.onTryAgainClicked()
        }
    }

    private fun setupRecyclerView() {
        charactersAdapter = CharactersAdapter() {
            viewModel.onCharacterClicked(it)
        }
        binding.rvCharacters.adapter = charactersAdapter.apply {
            withLoadStateFooter(
                footer = CharactersLoadStateAdapter()
            )
            addLoadStateListener { loadState ->
                viewModel.onLoadStateChanged(loadState)
            }
        }
    }

    private fun setupSearchView() = with(binding.searchView) {
        search.setOnFocusChangeListener { _, hasFocus ->
            viewModel.onSearchFocusChanged(hasFocus)
        }
        search.addTextChangedListener { text ->
            viewModel.onSearchTextChanged(text)
        }
        navigationIcon.setOnClickListener {
            viewModel.onSearchBackClicked()
        }
        clearText.setOnClickListener {
            viewModel.onSearchClearTextClicked()
        }
        binding.searchView.statusChipGroup.setOnCheckedStateChangeListener { _, checkedIds ->
            viewModel.onStatusSelected(checkedIds)
        }
    }

    private fun observeViewState() {
        viewModel.viewState.characters.observe(viewLifecycleOwner) { data ->
            data?.let {
                charactersAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
        viewModel.viewState.action.observe(viewLifecycleOwner) { action ->
            when (action) {
                is CharactersViewAction.OpenCharacterDetails -> openCharactersScreen(
                    action.characterId
                )

                is CharactersViewAction.UpdateSearchKeyboardFocus -> updateSearchKeyboardFocus(
                    action.hasFocus
                )

                is CharactersViewAction.UpdateSearchText -> updateSearchText(action.text)

                CharactersViewAction.FocusOnSearch -> focusOnSearch()
            }
        }
    }

    private fun updateSearchKeyboardFocus(hasFocus: Boolean) {
        binding.searchView.search.setFocusWithKeyboard(hasFocus)
    }

    private fun updateSearchText(text: String) {
        binding.searchView.search.setText(text)
    }

    private fun focusOnSearch() {
        binding.searchView.search.setFocusWithKeyboard(true)
        binding.rvCharacters.scrollToPosition(LIST_FIRST_POSITION)
        binding.rvCharacters.runWhenInteracted {
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