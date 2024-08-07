package br.com.lira.rickandmorty.locations.presentation.viewmodel

import androidx.paging.PagingData
import br.lira.core.presentation.viewmodel.ViewState
import br.com.lira.rickandmorty.locations.presentation.model.LocationUIModel
import br.lira.core.presentation.model.GenericUIError

data class LocationsListViewState(
    val locations: PagingData<LocationUIModel> = PagingData.empty(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val shouldDisplayContent: Boolean = false,
    val error: GenericUIError? = null
) : ViewState {

    fun setSuccessState() = this.copy(
        shouldDisplayContent = true,
        isLoading = false,
        isError = false
    )

    fun setLoadingState() = this.copy(
        shouldDisplayContent = false,
        isLoading = true,
        isError = false
    )

    fun setErrorState(error: GenericUIError) = this.copy(
        shouldDisplayContent = false,
        isLoading = false,
        isError = true,
        error = error
    )
}
