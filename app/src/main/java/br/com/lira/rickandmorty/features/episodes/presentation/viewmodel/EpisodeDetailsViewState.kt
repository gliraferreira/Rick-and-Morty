package br.com.lira.rickandmorty.features.episodes.presentation.viewmodel

import br.com.lira.rickandmorty.core.viewmodel.ViewState
import br.com.lira.rickandmorty.main.presentation.model.CharacterUIModel
import br.com.lira.rickandmorty.features.episodes.presentation.model.EpisodeDetailsUIModel
import br.com.lira.rickandmorty.main.domain.model.CharacterShort
import br.com.lira.rickandmorty.main.presentation.model.GenericUIError

data class EpisodeDetailsViewState(
    val episode: EpisodeDetailsUIModel? = null,
    val characters: List<CharacterUIModel> = emptyList(),
    val charactersHeader: String = "",
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val shouldDisplayContent: Boolean = false,
    val error: GenericUIError? = null,
    val shouldDisplayCharacters: Boolean = false,
    val isCharactersLoading: Boolean = false
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

    fun setCharactersSuccessState(characters: List<CharacterUIModel>, header: String) = this.copy(
        shouldDisplayCharacters = true,
        isCharactersLoading = false,
        characters = characters,
        charactersHeader = header
    )

    fun setCharactersLoadingState() = this.copy(
        shouldDisplayCharacters = false,
        isCharactersLoading = true
    )

    fun setCharactersErrorState() = this.copy(
        shouldDisplayCharacters = false,
        isCharactersLoading = false
    )
}
