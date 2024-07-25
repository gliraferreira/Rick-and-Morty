package br.com.lira.rickandmorty.locations.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.lira.rickandmorty.characters.navigation.CharacterNavigator
import br.com.lira.rickandmorty.characters.presentation.adapter.CharactersListAdapter
import br.com.lira.rickandmorty.locations.impl.R
import br.com.lira.rickandmorty.locations.impl.databinding.FragmentLocationDetailsBinding
import br.com.lira.rickandmorty.locations.presentation.viewmodel.LocationDetailsViewAction
import br.com.lira.rickandmorty.locations.presentation.viewmodel.LocationDetailsViewModel
import br.com.lira.rickandmorty.locations.presentation.viewmodel.LocationDetailsViewState
import br.com.lira.rickandmorty.navigation.ImmersiveNavigationMode
import br.com.lira.rickandmorty.navigation.NavigationModeHandler
import br.com.lira.rickandmorty.navigation.addPopBackStackHandler
import br.com.lira.rickandmorty.navigation.popBackStack
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val ARG_LOCATION_ID = "location_id"

@AndroidEntryPoint
class LocationDetailsFragment : Fragment(), NavigationModeHandler by ImmersiveNavigationMode {

    private var locationId: Long? = null
    private lateinit var binding: FragmentLocationDetailsBinding
    private val viewModel: LocationDetailsViewModel by viewModels()

    private lateinit var charactersAdapter: CharactersListAdapter

    @Inject
    lateinit var characterNavigator: CharacterNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            locationId = it.getLong(ARG_LOCATION_ID)
        }

        addPopBackStackHandler()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentLocationDetailsBinding.inflate(inflater).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.init(locationId)
        setupToolbar()
        setupViews()
        observeViewState()
        observeActions()
    }

    private fun setupViews() {
        charactersAdapter = CharactersListAdapter(true) {
            viewModel.onCharacterClicked(it)
        }
        binding.content.charactersList.adapter = charactersAdapter
    }

    private fun setupToolbar() = with(binding.toolbarView) {
        title.setText(R.string.location_details_title)
        searchIcon.isVisible = false
        navigationIcon.isVisible = true
        navigationIcon.setOnClickListener {
            popBackStack()
        }
    }

    private fun observeActions() {
        viewModel.action.observe(viewLifecycleOwner) { action ->
            when (action) {
                is LocationDetailsViewAction.OpenCharacterDetails -> {
                    openCharactersScreen(action.characterId)
                }
            }
        }
    }

    private fun observeViewState() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            binding.loading.root.isVisible = state.isLoading
            binding.content.root.isVisible = state.shouldDisplayContent
            binding.errorState.root.isVisible = state.isError

            handleLocation(state)
            handleCharactersList(state)
            handleError(state)
        }
    }

    private fun handleCharactersList(state: LocationDetailsViewState) = with(binding.content) {
        charactersList.isVisible = state.shouldDisplayCharacters
        episodesLoading.root.isVisible = state.isCharactersLoading
        charactersListHeader.text = state.charactersHeader
        charactersAdapter.submitList(state.characters)
    }

    private fun handleLocation(state: LocationDetailsViewState) = with(binding.content) {
        state.location?.let { locationUi ->
            locationName.text = locationUi.name
            locationType.text = locationUi.type
            locationDimension.text = locationUi.dimension
        }
    }

    private fun handleError(state: LocationDetailsViewState) = with(binding.errorState) {
        state.error?.let {
            description.text = it.message
            btnTryAgain.isVisible = it.isTryAgainVisible
            errorImageView.setImageDrawable(it.image)
        }
    }

    private fun openCharactersScreen(characterId: Long) {
        characterNavigator.openCharacterDetails(this, characterId)
    }

    companion object {
        fun newInstance(locationId: Long) = LocationDetailsFragment().apply {
            arguments = Bundle().apply {
                putLong(ARG_LOCATION_ID, locationId)
            }
        }
    }
}