package br.com.lira.rickandmorty.features.characterdetails.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.lira.rickandmorty.features.characterdetails.presentation.view.adapter.CharacterEpisodeAdapter
import br.com.lira.rickandmorty.features.characterdetails.presentation.viewmodel.CharacterDetailsViewModel
import br.com.lira.rickandmorty.databinding.FragmentCharacterDetailsBinding
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
            viewState = viewModel.viewState
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.init(characterId)
        setupViews()
        observeViewState()
    }

    private fun setupViews() {
        episodesAdapter = CharacterEpisodeAdapter()
        binding.content.rvEpisodes.adapter = episodesAdapter
    }

    private fun observeViewState() {
        viewModel.viewState.episodes.observe(viewLifecycleOwner) {
            episodesAdapter.submitList(it)
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