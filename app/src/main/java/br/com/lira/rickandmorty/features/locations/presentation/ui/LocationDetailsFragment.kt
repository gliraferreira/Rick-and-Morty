package br.com.lira.rickandmorty.features.locations.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.lira.rickandmorty.R
import br.com.lira.rickandmorty.core.toolkit.addPopBackStackHandler
import br.com.lira.rickandmorty.core.toolkit.popBackStack
import br.com.lira.rickandmorty.databinding.FragmentLocationDetailsBinding
import br.com.lira.rickandmorty.features.locations.presentation.viewmodel.LocationDetailsViewModel
import br.com.lira.rickandmorty.features.locations.presentation.viewmodel.LocationDetailsViewState
import br.com.lira.rickandmorty.features.shared.presentation.adapter.CharactersListAdapter
import dagger.hilt.android.AndroidEntryPoint

private const val ARG_LOCATION_ID = "location_id"

@AndroidEntryPoint
class LocationDetailsFragment : Fragment() {

    private var locationId: Long? = null
    private lateinit var binding: FragmentLocationDetailsBinding
    private val viewModel: LocationDetailsViewModel by viewModels()

    private lateinit var charactersAdapter: CharactersListAdapter

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
    }

    private fun setupViews() {
        charactersAdapter = CharactersListAdapter(false) { }
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

    companion object {
        fun newInstance(locationId: Long) = LocationDetailsFragment().apply {
            arguments = Bundle().apply {
                putLong(ARG_LOCATION_ID, locationId)
            }
        }
    }
}