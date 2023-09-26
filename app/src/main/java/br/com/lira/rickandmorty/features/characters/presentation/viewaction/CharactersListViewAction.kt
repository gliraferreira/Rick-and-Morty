package br.com.lira.rickandmorty.features.characters.presentation.viewaction

import br.com.lira.rickandmorty.core.viewmodel.ViewAction
import br.com.lira.rickandmorty.features.characters.domain.model.CharacterFilter

sealed class CharactersListViewAction : ViewAction {

    data class OpenCharacterDetails(val characterId: Long) : CharactersListViewAction()
    data class OpenCharacterFilter(val currentFilter: CharacterFilter?) : CharactersListViewAction()
}
