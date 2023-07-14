package br.com.lira.rickandmorty.features.characterdetails.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.lira.rickandmorty.core.extension.loadImage
import br.com.lira.rickandmorty.databinding.FragmentCharacterDetailsBinding
import br.com.lira.rickandmorty.features.characterdetails.presentation.view.adapter.CharacterEpisodeAdapter
import br.com.lira.rickandmorty.features.characterdetails.presentation.viewmodel.CharacterDetailsViewModel
import br.com.lira.rickandmorty.features.characterdetails.presentation.viewmodel.CharacterDetailsViewState
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
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailsBinding.inflate(inflater).apply {
            lifecycleOwner = this@CharacterDetailsFragment
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.init(characterId)
        setupToolbar()
        setupViews()
        observeViewState()
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
            episodesAdapter.submitList(state.episodes)
            binding.loading.root.isVisible = state.isLoading
            binding.content.root.isVisible = state.shouldDisplayContent
            handleCharacterContent(state)
        }
    }

    private fun handleCharacterContent(state: CharacterDetailsViewState) = with(binding.content) {
        state.character?.let { character ->

            binding.toolbarView.title.text = character.name
            characterImage.loadImage(character.image)
            tvName.text = character.name
            tvLocation.text = character.lastLocation
            tvGender.setText(character.gender)
            status.setChipBackgroundColorResource(character.statusColor)
            status.setText(character.statusText)
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