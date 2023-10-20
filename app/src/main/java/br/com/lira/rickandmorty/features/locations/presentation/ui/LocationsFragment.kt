package br.com.lira.rickandmorty.features.locations.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.PagingData
import br.com.lira.rickandmorty.R
import br.com.lira.rickandmorty.databinding.FragmentLocationsBinding
import br.com.lira.rickandmorty.features.locations.presentation.model.LocationItemUI
import br.com.lira.rickandmorty.features.locations.presentation.ui.adapter.LocationsAdapter
import br.com.lira.rickandmorty.features.locations.presentation.viewmodel.LocationsListViewModel
import br.com.lira.rickandmorty.features.locations.presentation.viewmodel.LocationsListViewState
import br.com.lira.rickandmorty.features.shared.presentation.adapter.PagingLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LocationsFragment : Fragment() {

    private lateinit var binding: FragmentLocationsBinding
    private val viewModel: LocationsListViewModel by viewModels()

    private lateinit var locationsAdapter: LocationsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentLocationsBinding.inflate(layoutInflater).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        observeViewState()
    }

    private fun setupViews() {
        setupToolbar()
        setupRecyclerView()
        setupErrorView()
    }

    private fun setupToolbar() {
        binding.toolbarView.navigationIcon.isVisible = false
        binding.toolbarView.searchIcon.isVisible = false
        binding.toolbarView.title.setText(R.string.title_locations)
    }

    private fun setupRecyclerView() {
        locationsAdapter = LocationsAdapter()
        binding.locationsLIst.adapter = locationsAdapter.apply {
            withLoadStateFooter(
                footer = PagingLoadStateAdapter()
            )
            addLoadStateListener { loadState ->
                viewModel.onLoadStateChanged(loadState)
            }
        }
    }

    private fun setupErrorView() {
        binding.errorState.btnTryAgain.setOnClickListener {
            viewModel.onTryAgainClicked()
        }
    }

    private fun handleError(state: LocationsListViewState) = with(binding.errorState) {
        state.error?.let {
            description.text = it.message
            btnTryAgain.isVisible = it.isTryAgainVisible
            errorImageView.setImageDrawable(it.image)
        }
    }

    private fun observeViewState() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            handleLocationsList(state.locations)
            handleCurrentScreenState(state)
            handleError(state)
        }
    }

    private fun handleLocationsList(locations: PagingData<LocationItemUI>?) {
        locations?.let {
            locationsAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    private fun handleCurrentScreenState(state: LocationsListViewState) = with(binding) {
        loading.root.isVisible = state.isLoading
        locationsLIst.isVisible = state.shouldDisplayContent
        errorState.root.isVisible = state.isError
    }
}