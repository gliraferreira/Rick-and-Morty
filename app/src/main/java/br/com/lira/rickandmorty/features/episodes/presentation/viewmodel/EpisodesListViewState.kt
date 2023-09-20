package br.com.lira.rickandmorty.features.episodes.presentation.viewmodel

import androidx.paging.PagingData
import br.com.lira.rickandmorty.core.viewmodel.ViewState
import br.com.lira.rickandmorty.main.presentation.model.GenericError
import br.com.lira.rickandmorty.features.episodes.presentation.model.EpisodeUIModel

data class EpisodesListViewState(
    val episodes: PagingData<EpisodeUIModel>? = PagingData.empty(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val shouldDisplayContent: Boolean = false,
    val error: GenericError? = null,
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

    fun setErrorState(error: GenericError) = this.copy(
        shouldDisplayContent = false,
        isLoading = false,
        isError = true,
        error = error
    )
}
