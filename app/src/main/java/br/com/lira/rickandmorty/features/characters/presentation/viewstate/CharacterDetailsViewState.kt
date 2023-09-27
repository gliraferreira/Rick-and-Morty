package br.com.lira.rickandmorty.features.characters.presentation.viewstate

import br.com.lira.rickandmorty.core.viewmodel.ViewState
import br.com.lira.rickandmorty.features.characters.presentation.model.CharacterDetailsUIModel
import br.com.lira.rickandmorty.features.characters.presentation.model.CharacterEpisodeUIModel

data class CharacterDetailsViewState(
    val character: CharacterDetailsUIModel? = null,
    val episodes: List<CharacterEpisodeUIModel> = emptyList(),
    val episodesHeader: String = "",
    val shouldDisplayContent: Boolean = false,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val shouldDisplayEpisodes: Boolean = false,
    val isEpisodesLoading: Boolean = false
) : ViewState {

    fun setSuccessState(character: CharacterDetailsUIModel) = this.copy(
        shouldDisplayContent = true,
        isLoading = false,
        isError = false,
        character = character
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
