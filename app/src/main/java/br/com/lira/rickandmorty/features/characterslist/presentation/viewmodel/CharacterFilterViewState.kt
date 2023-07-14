package br.com.lira.rickandmorty.features.characterslist.presentation.viewmodel

import br.com.lira.rickandmorty.core.viewmodel.ViewState
import br.com.lira.rickandmorty.features.characterslist.domain.model.CharacterFilter
import br.com.lira.rickandmorty.main.domain.model.CharacterGender
import br.com.lira.rickandmorty.main.domain.model.CharacterStatus

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
