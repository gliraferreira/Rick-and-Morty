package br.com.lira.rickandmorty.locations.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.PagingData
import br.com.lira.rickandmorty.locations.impl.R
import br.com.lira.rickandmorty.locations.impl.databinding.FragmentLocationsBinding
import br.com.lira.rickandmorty.locations.navigation.LocationNavigator
import br.com.lira.rickandmorty.locations.presentation.model.LocationUIModel
import br.com.lira.rickandmorty.locations.presentation.ui.adapter.LocationsAdapter
import br.com.lira.rickandmorty.locations.presentation.viewmodel.LocationsListViewAction
import br.com.lira.rickandmorty.locations.presentation.viewmodel.LocationsListViewModel
import br.com.lira.rickandmorty.locations.presentation.viewmodel.LocationsListViewState
import br.com.lira.rickandmorty.navigation.DefaultNavigationMode
import br.com.lira.rickandmorty.navigation.NavigationModeHandler
import br.lira.core.presentation.adapter.PagingLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LocationsFragment : Fragment(), NavigationModeHandler by DefaultNavigationMode {

    private lateinit var binding: FragmentLocationsBinding
    private val viewModel: LocationsListViewModel by viewModels()

    private lateinit var locationsAdapter: LocationsAdapter

    @Inject
    lateinit var locationNavigator: LocationNavigator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentLocationsBinding.inflate(layoutInflater).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        observeViewState()
        observeViewActions()
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
        locationsAdapter = LocationsAdapter(viewModel::onLocationClicked)
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

    private fun observeViewActions() {
        viewModel.action.observe(viewLifecycleOwner) { viewAction ->
            when (viewAction) {
                is LocationsListViewAction.OpenLocationDetails -> openLocationDetailsScreen(
                    viewAction.locationId
                )
            }
        }
    }

    private fun observeViewState() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            handleLocationsList(state.locations)
            handleCurrentScreenState(state)
            handleError(state)
        }
    }

    private fun handleLocationsList(locations: PagingData<LocationUIModel>?) {
        locations?.let {
            locationsAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    private fun handleCurrentScreenState(state: LocationsListViewState) = with(binding) {
        loading.root.isVisible = state.isLoading
        locationsLIst.isVisible = state.shouldDisplayContent
        errorState.root.isVisible = state.isError
    }

    private fun openLocationDetailsScreen(locationId: Long) {
        locationNavigator.openLocationDetails(this, locationId)
    }
}