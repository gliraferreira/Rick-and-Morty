package br.com.lira.rickandmorty.features.characters.presentation.viewstate

import androidx.paging.PagingData
import br.lira.core.presentation.viewmodel.ViewState
import br.com.lira.rickandmorty.features.characters.domain.model.CharacterFilter
import br.lira.core.presentation.model.GenericUIError
import br.com.lira.rickandmorty.features.shared.presentation.model.CharacterUIModel

data class CharactersListViewState(
    val characters: PagingData<CharacterUIModel>? = PagingData.empty(),
    val filter: CharacterFilter? = CharacterFilter(),
    val error: GenericUIError? = null,
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

    fun setErrorState(error: GenericUIError) = this.copy(
        shouldDisplayContent = false,
        isLoading = false,
        isError = true,
        error = error
    )
}
