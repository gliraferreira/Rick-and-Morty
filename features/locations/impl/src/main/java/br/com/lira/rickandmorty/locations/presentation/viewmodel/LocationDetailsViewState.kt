package br.com.lira.rickandmorty.locations.presentation.viewmodel

import br.com.lira.rickandmorty.characters.presentation.model.CharacterUIModel
import br.com.lira.rickandmorty.locations.presentation.model.LocationUIModel
import br.lira.core.presentation.model.GenericUIError
import br.lira.core.presentation.viewmodel.ViewState

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
