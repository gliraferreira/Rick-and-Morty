package br.com.lira.rickandmorty.features.characterslist.presentation.viewmodel

import br.com.lira.rickandmorty.core.viewmodel.ViewAction

sealed class CharactersViewAction : ViewAction {

    data class OpenCharacterDetails(val characterId: Long) : CharactersViewAction()
    data class UpdateSearchKeyboardFocus(val hasFocus: Boolean) : CharactersViewAction()
    data class UpdateSearchText(val text: String) : CharactersViewAction()
    object FocusOnSearch : CharactersViewAction()
}