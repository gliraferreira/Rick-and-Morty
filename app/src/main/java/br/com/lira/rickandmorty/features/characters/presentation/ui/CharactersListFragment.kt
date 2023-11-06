package br.com.lira.rickandmorty.features.characters.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.paging.PagingData
import br.com.lira.rickandmorty.R
import br.com.lira.rickandmorty.core.toolkit.SlideFromRightAnimation
import br.com.lira.rickandmorty.core.toolkit.navigateToFragment
import br.com.lira.rickandmorty.databinding.FragmentCharactersBinding
import br.com.lira.rickandmorty.features.characters.domain.model.CharacterFilter
import br.com.lira.rickandmorty.features.shared.presentation.model.CharacterUIModel
import br.com.lira.rickandmorty.features.characters.presentation.ui.adapter.CharactersPagingListAdapter
import br.com.lira.rickandmorty.features.shared.presentation.adapter.PagingLoadStateAdapter
import br.com.lira.rickandmorty.features.characters.presentation.viewaction.CharactersListViewAction
import br.com.lira.rickandmorty.features.characters.presentation.viewmodel.CharactersListViewModel
import br.com.lira.rickandmorty.features.characters.presentation.viewstate.CharactersListViewState
import br.com.lira.rickandmorty.main.navigation.DefaultNavigationMode
import br.com.lira.rickandmorty.main.navigation.NavigationModeHandler
import dagger.hilt.android.AndroidEntryPoint

const val FILTER_REQUEST_KEY = "filter_request_key"
const val ARG_FILTER = "arg_filter"

@AndroidEntryPoint
class CharactersFragment : Fragment(), NavigationModeHandler by DefaultNavigationMode {

    private lateinit var binding: FragmentCharactersBinding
    private val viewModel: CharactersListViewModel by viewModels()

    private lateinit var charactersAdapter: CharactersPagingListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        observeViewState()
        observeActions()
    }

    private fun setupViews() {
        setupToolbar()
        setupRecyclerView()
        setupErrorView()
        setupFilterResultListener()
        setupSearchView()
    }

    private fun setupSearchView() {
        binding.searchView.btnClearFilters.setOnClickListener {
            viewModel.onClearFiltersClicked()
        }
    }

    private fun setupFilterResultListener() {
        setFragmentResultListener(FILTER_REQUEST_KEY) { _, bundle ->
            val selectedFilter = bundle.getParcelable<CharacterFilter>(ARG_FILTER)
            viewModel.onCharacterFilterUpdated(selectedFilter)
        }
    }

    private fun setupToolbar() {
        binding.toolbarView.searchIcon.setOnClickListener {
            viewModel.onSearchClicked()
        }
        binding.toolbarView.searchIcon.isVisible = true
        binding.toolbarView.navigationIcon.isVisible = false
        binding.toolbarView.title.setText(R.string.title_characters)
    }

    private fun setupErrorView() {
        binding.errorState.btnTryAgain.setOnClickListener {
            viewModel.onTryAgainClicked()
        }
    }

    private fun setupRecyclerView() {
        charactersAdapter = CharactersPagingListAdapter(true) {
            viewModel.onCharacterClicked(it)
        }
        binding.rvCharacters.adapter = charactersAdapter.apply {
            withLoadStateFooter(
                footer = PagingLoadStateAdapter()
            )
            addLoadStateListener { loadState ->
                viewModel.onLoadStateChanged(loadState)
            }
        }
    }

    private fun observeViewState() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            handleCharactersList(state.characters)
            updateSearchView(state)
            handleCurrentScreenState(state)
            handleError(state)
        }
    }

    private fun observeActions() {
        viewModel.action.observe(viewLifecycleOwner) { action ->
            when (action) {
                is CharactersListViewAction.OpenCharacterDetails -> openCharactersScreen(
                    action.characterId
                )

                is CharactersListViewAction.OpenCharacterFilter -> openCharacterFilterScreen(
                    action.currentFilter
                )
            }
        }
    }

    private fun handleError(state: CharactersListViewState) = with(binding.errorState) {
        state.error?.let {
            description.text = it.message
            btnTryAgain.isVisible = it.isTryAgainVisible
            errorImageView.setImageDrawable(it.image)
        }
    }

    private fun handleCurrentScreenState(state: CharactersListViewState) = with(binding) {
        loading.root.isVisible = state.isLoading
        rvCharacters.isVisible = state.shouldDisplayContent
        errorState.root.isVisible = state.isError
    }

    private fun updateSearchView(state: CharactersListViewState) = with(binding) {
        searchView.root.isVisible = state.isFilteringResults
        searchView.tvFilterDetails.text = state.filterDetails
    }

    private fun handleCharactersList(characters: PagingData<CharacterUIModel>?) {
        characters?.let {
            charactersAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    private fun openCharacterFilterScreen(currentFilter: CharacterFilter?) {
        navigateToFragment(
            hostRes = R.id.app_nav_host_fragment,
            destination = CharacterFilterFragment.newInstance(currentFilter),
            fragmentAnimation = SlideFromRightAnimation
        )
    }

    private fun openCharactersScreen(characterId: Long) {
        navigateToFragment(
            hostRes = R.id.app_nav_host_fragment,
            destination = CharacterDetailsFragment.newInstance(characterId),
            fragmentAnimation = SlideFromRightAnimation
        )
    }
}
