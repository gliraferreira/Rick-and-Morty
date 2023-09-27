package br.com.lira.rickandmorty.features.episodes.presentation.viewmodel

import br.com.lira.rickandmorty.core.viewmodel.ViewState
import br.com.lira.rickandmorty.main.presentation.model.CharacterUIModel
import br.com.lira.rickandmorty.features.episodes.presentation.model.EpisodeDetailsUIModel
import br.com.lira.rickandmorty.main.presentation.model.GenericUIError

data class EpisodeDetailsViewState(
    val episode: EpisodeDetailsUIModel? = null,
    val characters: List<CharacterUIModel> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val shouldDisplayContent: Boolean = false,
    val error: GenericUIError? = null,
) : ViewState {

    fun setSuccessState(episode: EpisodeDetailsUIModel) = this.copy(
        shouldDisplayContent = true,
        isLoading = false,
        isError = false,
        episode = episode
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
