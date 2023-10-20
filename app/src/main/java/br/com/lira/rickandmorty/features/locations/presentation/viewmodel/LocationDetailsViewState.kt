package br.com.lira.rickandmorty.features.locations.presentation.viewmodel

import br.com.lira.rickandmorty.core.viewmodel.ViewState
import br.com.lira.rickandmorty.features.shared.presentation.model.CharacterUIModel
import br.com.lira.rickandmorty.features.episodes.presentation.model.EpisodeDetailsUIModel
import br.com.lira.rickandmorty.features.locations.presentation.model.LocationUIModel
import br.com.lira.rickandmorty.features.shared.presentation.model.GenericUIError

data class LocationDetailsViewState(
    val location: LocationUIModel? = null,
    val characters: List<CharacterUIModel> = emptyList(),
    val charactersHeader: String = "",
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val shouldDisplayContent: Boolean = false,
    val error: GenericUIError? = null,
    val shouldDisplayCharacters: Boolean = false,
    val isCharactersLoading: Boolean = false
) : ViewState {

    fun setSuccessState(location: LocationUIModel) = this.copy(
        shouldDisplayContent = true,
        isLoading = false,
        isError = false,
        location = location
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

    fun setCharactersErrorState(header: String) = this.copy(
        shouldDisplayCharacters = false,
        isCharactersLoading = false,
        charactersHeader = header
    )
}
