package br.com.lira.rickandmorty.features.locations.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.cachedIn
import androidx.paging.map
import br.com.lira.rickandmorty.core.viewmodel.ViewModel
import br.com.lira.rickandmorty.features.locations.domain.usecase.GetAllLocationsUseCase
import br.com.lira.rickandmorty.features.locations.presentation.mapper.LocationShortToItemUIMapper
import br.com.lira.rickandmorty.features.locations.presentation.mapper.LocationsErrorMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@HiltViewModel
class LocationsListViewModel @Inject constructor(
    private val getAllLocations: GetAllLocationsUseCase,
    private val locationUiMapper: LocationShortToItemUIMapper,
    private val errorMapper: LocationsErrorMapper
) : ViewModel<LocationsListViewState, LocationsListViewAction>(LocationsListViewState())  {

    init {
        loadLocations()
    }

    fun onLoadStateChanged(loadState: CombinedLoadStates) {
        when (val refresh = loadState.source.refresh) {
            is LoadState.NotLoading -> setState { it.setSuccessState() }
            is LoadState.Loading -> setState { it.setLoadingState() }
            is LoadState.Error -> handleErrorState(refresh)
        }
    }

    fun onTryAgainClicked() {
        loadLocations()
    }

    fun onLocationClicked(locationId: Long) {
        sendAction { LocationsListViewAction.OpenLocationDetails(locationId) }
    }

    private fun loadLocations() {
        viewModelScope.launch {
            getAllLocations()
                .map { it.map(locationUiMapper::mapFrom) }
                .cachedIn(viewModelScope)
                .collect { result -> setState { it.copy(locations = result) } }
        }
    }

    private fun handleErrorState(refresh: LoadState.Error) {
        val error = errorMapper.mapFrom(refresh.error)
        setState { it.setErrorState(error) }
    }
}