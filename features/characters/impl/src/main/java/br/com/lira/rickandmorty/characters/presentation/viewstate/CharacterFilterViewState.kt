package br.com.lira.rickandmorty.characters.presentation.viewstate

import br.lira.core.presentation.viewmodel.ViewState
import br.com.lira.rickandmorty.characters.domain.model.CharacterFilter
import br.com.lira.rickandmorty.characters.domain.model.CharacterGender
import br.com.lira.rickandmorty.characters.domain.model.CharacterStatus

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
