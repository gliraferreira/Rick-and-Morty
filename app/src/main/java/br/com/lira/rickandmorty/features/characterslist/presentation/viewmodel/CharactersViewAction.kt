package br.com.lira.rickandmorty.features.characterslist.presentation.viewmodel

import br.com.lira.rickandmorty.core.viewmodel.ViewAction
import br.com.lira.rickandmorty.features.characterslist.domain.model.CharacterFilter

sealed class CharactersViewAction : ViewAction {

    data class OpenCharacterDetails(val characterId: Long) : CharactersViewAction()
    data class OpenCharacterFilter(val currentFilter: CharacterFilter?) : CharactersViewAction()
}
