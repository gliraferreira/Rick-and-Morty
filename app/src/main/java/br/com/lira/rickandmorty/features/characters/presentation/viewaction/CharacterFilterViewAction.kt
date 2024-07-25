package br.com.lira.rickandmorty.features.characters.presentation.viewaction

import br.lira.core.presentation.viewmodel.ViewAction
import br.com.lira.rickandmorty.characters.domain.model.CharacterFilter
import br.com.lira.rickandmorty.features.characters.presentation.model.CharacterFilterUIModel

sealed class CharacterFilterViewAction : ViewAction {

    data class SendFilterResult(val filter: CharacterFilter?) : CharacterFilterViewAction()
    data class UpdateUI(val filter: CharacterFilterUIModel) : CharacterFilterViewAction()
}
