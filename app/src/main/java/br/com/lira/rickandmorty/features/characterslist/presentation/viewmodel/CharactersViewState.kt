package br.com.lira.rickandmorty.features.characterslist.presentation.viewmodel

import androidx.paging.PagingData
import br.com.lira.rickandmorty.core.viewmodel.ViewState
import br.com.lira.rickandmorty.features.characterslist.domain.model.CharacterFilter
import br.com.lira.rickandmorty.main.presentation.model.GenericError
import br.com.lira.rickandmorty.features.characterslist.presentation.model.CharacterUIModel

data class CharactersViewState(
    val characters: PagingData<CharacterUIModel>? = PagingData.empty(),
    val filter: CharacterFilter? = CharacterFilter(),
    val error: GenericError? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val shouldDisplayContent: Boolean = false,
    val isSearchEnabled: Boolean = false,
    val isFilteringResults: Boolean = false,
    val filterDetails: String = ""
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
