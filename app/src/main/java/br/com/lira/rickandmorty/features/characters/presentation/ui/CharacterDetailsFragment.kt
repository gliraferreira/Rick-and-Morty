package br.com.lira.rickandmorty.features.characters.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.lira.rickandmorty.R
import br.com.lira.rickandmorty.core.extension.loadImage
import br.com.lira.rickandmorty.core.toolkit.navigateToFragment
import br.com.lira.rickandmorty.core.toolkit.popBackStack
import br.com.lira.rickandmorty.databinding.FragmentCharacterDetailsBinding
import br.com.lira.rickandmorty.features.characters.presentation.ui.adapter.CharacterEpisodeAdapter
import br.com.lira.rickandmorty.features.characters.presentation.viewmodel.CharacterDetailsViewModel
import br.com.lira.rickandmorty.features.characters.presentation.viewstate.CharacterDetailsViewState
import br.com.lira.rickandmorty.features.episodes.presentation.ui.EpisodeDetailsFragment
import br.com.lira.rickandmorty.features.episodes.presentation.viewmodel.EpisodesListViewAction
import dagger.hilt.android.AndroidEntryPoint

private const val ARG_CHARACTER_ID = "char_id"

@AndroidEntryPoint
class CharacterDetailsFragment : Fragment() {

    private var characterId: Long? = null
    private lateinit var binding: FragmentCharacterDetailsBinding
    private val viewModel: CharacterDetailsViewModel by viewModels()

    private lateinit var episodesAdapter: CharacterEpisodeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            characterId = it.getLong(ARG_CHARACTER_ID)
        }
        setupBackPressed()
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
    }

    private fun setupBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            popBackStack()
        }
    }

    private fun setupViews() {
        episodesAdapter = CharacterEpisodeAdapter()
        binding.content.rvEpisodes.adapter = episodesAdapter
        binding.toolbarView.navigationIcon.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun setupToolbar() {
        binding.toolbarView.searchIcon.isVisible = false
        binding.toolbarView.navigationIcon.isVisible = true
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
            tvLocation.text = character.lastLocation
            tvGender.text = character.gender
            tvSpecies.text = character.species
        }
    }

    companion object {
        fun newInstance(characterId: Long) = CharacterDetailsFragment().apply {
            arguments = Bundle().apply {
                putLong(ARG_CHARACTER_ID, characterId)
            }
        }
    }
}