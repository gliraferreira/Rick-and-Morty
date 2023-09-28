package br.com.lira.rickandmorty.features.characters.presentation.viewstate

import br.com.lira.rickandmorty.core.viewmodel.ViewState
import br.com.lira.rickandmorty.features.characters.domain.model.CharacterFilter
import br.com.lira.rickandmorty.features.shared.domain.model.CharacterGender
import br.com.lira.rickandmorty.features.shared.domain.model.CharacterStatus

data class CharacterFilterViewState(
    val filter: CharacterFilter? = CharacterFilter()
) : ViewState {

    fun updateStatus(status: CharacterStatus?) = this.copy(
        filter = this.filter?.copy(status = status)
    )

    fun updateGender(gender: CharacterGender?) = this.copy(
        filter = this.filter?.copy(gender = gender)
    )
}
