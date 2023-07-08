package br.com.lira.rickandmorty.features.characterslist.presentation.viewmodel

import br.com.lira.rickandmorty.core.viewmodel.ViewAction
import br.com.lira.rickandmorty.features.characterslist.domain.model.CharacterFilter
import br.com.lira.rickandmorty.features.characterslist.presentation.model.CharacterFilterUIModel

sealed class CharacterFilterViewAction : ViewAction {

    data class SendFilterResult(val filter: CharacterFilter?) : CharacterFilterViewAction()
    data class UpdateUI(val filter: CharacterFilterUIModel) : CharacterFilterViewAction()
}
