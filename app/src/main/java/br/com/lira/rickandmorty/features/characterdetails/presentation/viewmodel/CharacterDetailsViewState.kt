package br.com.lira.rickandmorty.features.characterdetails.presentation.viewmodel

import br.com.lira.rickandmorty.core.viewmodel.ViewState
import br.com.lira.rickandmorty.features.characterdetails.presentation.model.CharacterDetailsUIModel
import br.com.lira.rickandmorty.features.characterdetails.presentation.model.CharacterEpisodeUIModel

data class CharacterDetailsViewState(
    val character: CharacterDetailsUIModel? = null,
    val episodes: List<CharacterEpisodeUIModel> = emptyList(),
    val shouldDisplayContent: Boolean = false,
    val isLoading: Boolean = false,
    val isError: Boolean = false
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
}
