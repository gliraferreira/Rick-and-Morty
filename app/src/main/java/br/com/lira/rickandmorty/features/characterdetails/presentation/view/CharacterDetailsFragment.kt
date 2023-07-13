package br.com.lira.rickandmorty.features.characterdetails.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.lira.rickandmorty.core.binding.loadImage
import br.com.lira.rickandmorty.core.binding.setBgColor
import br.com.lira.rickandmorty.databinding.FragmentCharacterDetailsBinding
import br.com.lira.rickandmorty.features.characterdetails.presentation.view.adapter.CharacterEpisodeAdapter
import br.com.lira.rickandmorty.features.characterdetails.presentation.viewmodel.CharacterDetailsViewModel
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
    ): View? {
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

    private fun observeViewState() = with(viewModel.viewState) {
        observeCharacter()
        episodes.observe(viewLifecycleOwner) {
            episodesAdapter.submitList(it)
        }
        isLoading.observe(viewLifecycleOwner) {
            binding.loading.root.isVisible = it
        }
        shouldDisplayContent.observe(viewLifecycleOwner) {
            binding.content.root.isVisible = it
        }
    }

    private fun observeCharacter() {
        viewModel.viewState.character.observe(viewLifecycleOwner) {
            binding.toolbarView.title.text = it.name
            with(binding.content) {
                characterImage.loadImage(it.image)
                tvName.text = it.name
                tvLocation.text = it.lastLocation
                tvGender.setText(it.gender)
                status.setBgColor(it.statusColor)
                status.setText(it.statusText)
                tvSpecies.text = it.species
            }
        }
    }

    companion object {
        fun newInstance(characterId: Long) =
            CharacterDetailsFragment().apply {
                arguments = Bundle().apply {
                    putLong(ARG_CHARACTER_ID, characterId)
                }
            }
    }
}