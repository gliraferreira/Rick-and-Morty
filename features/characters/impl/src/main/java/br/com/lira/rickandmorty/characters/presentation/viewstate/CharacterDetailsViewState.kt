package br.com.lira.rickandmorty.characters.presentation.viewstate

import br.lira.core.presentation.viewmodel.ViewState
import br.com.lira.rickandmorty.characters.presentation.model.CharacterDetailsUIModel
import br.com.lira.rickandmorty.characters.presentation.model.CharacterEpisodeUIModel

data class CharacterDetailsViewState(
    val character: CharacterDetailsUIModel? = null,
    val episodes: List<CharacterEpisodeUIModel> = emptyList(),
    val episodesHeader: String = "",
    val shouldDisplayContent: Boolean = false,
    val shouldDisplayCurrentLocation: Boolean = false,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val shouldDisplayEpisodes: Boolean = false,
    val isEpisodesLoading: Boolean = false
) : ViewState {

    fun setSuccessState(
        character: CharacterDetailsUIModel,
        isCurrentLocationVisible: Boolean
    ) = this.copy(
        shouldDisplayContent = true,
        isLoading = false,
        isError = false,
        character = character,
        shouldDisplayCurrentLocation = isCurrentLocationVisible
    )

    fun setLoadingState() = this.copy(
        shouldDisplayContent = false,
        isLoading = true,
        isError = false
    )

    fun setEpisodesLoadingState() = this.copy(
        shouldDisplayEpisodes = false,
        isEpisodesLoading = true
    )

    fun setEpisodesSuccessState(episodes: List<CharacterEpisodeUIModel>, header: String) =
        this.copy(
            shouldDisplayEpisodes = true,
            isEpisodesLoading = false,
            episodes = episodes,
            episodesHeader = header
        )

    fun setEpisodesErrorState() = this.copy(
        shouldDisplayEpisodes = false,
        isEpisodesLoading = false
    )
}
