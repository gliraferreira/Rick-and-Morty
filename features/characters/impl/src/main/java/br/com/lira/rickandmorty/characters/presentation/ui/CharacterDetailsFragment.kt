package br.com.lira.rickandmorty.characters.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.lira.rickandmorty.characters.impl.databinding.FragmentCharacterDetailsBinding
import br.com.lira.rickandmorty.characters.presentation.ui.adapter.CharacterEpisodeAdapter
import br.com.lira.rickandmorty.characters.presentation.viewaction.CharacterDetailsViewAction
import br.com.lira.rickandmorty.characters.presentation.viewmodel.CharacterDetailsViewModel
import br.com.lira.rickandmorty.characters.presentation.viewstate.CharacterDetailsViewState
import br.com.lira.rickandmorty.episodes.navigation.EpisodesNavigator
import br.com.lira.rickandmorty.locations.navigation.LocationNavigator
import br.com.lira.rickandmorty.navigation.ImmersiveNavigationMode
import br.com.lira.rickandmorty.navigation.NavigationModeHandler
import br.com.lira.rickandmorty.navigation.addPopBackStackHandler
import br.lira.core.presentation.extensions.loadImage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val ARG_CHARACTER_ID = "char_id"

@AndroidEntryPoint
class CharacterDetailsFragment : Fragment(), NavigationModeHandler by ImmersiveNavigationMode {

    private var characterId: Long? = null
    private lateinit var binding: FragmentCharacterDetailsBinding
    private val viewModel: CharacterDetailsViewModel by viewModels()

    private lateinit var episodesAdapter: CharacterEpisodeAdapter

    @Inject
    lateinit var episodeNavigator: EpisodesNavigator

    @Inject
    lateinit var locationNavigator: LocationNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            characterId = it.getLong(ARG_CHARACTER_ID)
        }
        addPopBackStackHandler()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailsBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.init(characterId)
        setupToolbar()
        setupViews()
        observeViewState()
        observeActions()
    }

    private fun setupViews() {
        episodesAdapter = CharacterEpisodeAdapter(viewModel::onEpisodeClicked)
        binding.content.rvEpisodes.adapter = episodesAdapter
        binding.toolbarView.navigationIcon.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun setupToolbar() {
        binding.toolbarView.searchIcon.isVisible = false
        binding.toolbarView.navigationIcon.isVisible = true
    }

    private fun observeActions() {
        viewModel.action.observe(viewLifecycleOwner) { action ->
            when (action) {
                is CharacterDetailsViewAction.OpenEpisodeDetails -> {
                    openEpisodeDetailsScreen(action.episodeId)
                }

                is CharacterDetailsViewAction.OpenCurrentLocationDetails -> {
                    openLocationDetailsScreen(action.locationId)
                }
            }
        }
    }

    private fun observeViewState() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            with(binding) {
                loading.root.isVisible = state.isLoading
                content.root.isVisible = state.shouldDisplayContent

                handleEpisodes(state)
            }

            handleCharacterContent(state)
        }
    }

    private fun handleEpisodes(state: CharacterDetailsViewState) = with(binding.content) {
        episodesLoading.root.isVisible = state.isEpisodesLoading
        rvEpisodes.isVisible = state.shouldDisplayEpisodes
        tvEpisodesHeader.isVisible = state.shouldDisplayEpisodes
        tvEpisodesHeader.text = state.episodesHeader
        episodesAdapter.submitList(state.episodes)
    }

    private fun handleCharacterContent(state: CharacterDetailsViewState) = with(binding.content) {
        state.character?.let { character ->

            binding.toolbarView.title.text = character.name

            characterImage.loadImage(character.image)
            characterImage.borderColor = character.statusColor

            tvName.text = character.name
            tvGender.text = character.gender
            tvSpecies.text = character.species

            tvLocationLabel.isVisible = state.shouldDisplayCurrentLocation
            btnLocation.isVisible = state.shouldDisplayCurrentLocation
            btnLocation.text = character.lastLocation
            btnLocation.setOnClickListener {
                viewModel.onCurrentLocationClicked()
            }
        }
    }

    private fun openEpisodeDetailsScreen(episodeId: Long) {
        episodeNavigator.openEpisodeDetails(this, episodeId)
    }

    private fun openLocationDetailsScreen(locationId: Long) {
        locationNavigator.openLocationDetails(this, locationId)
    }

    companion object {
        fun newInstance(characterId: Long) = CharacterDetailsFragment().apply {
            arguments = Bundle().apply {
                putLong(ARG_CHARACTER_ID, characterId)
            }
        }
    }
}