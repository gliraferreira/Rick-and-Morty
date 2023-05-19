package br.com.lira.rickandmorty.features.characterslist.presentation.viewmodel

import br.com.lira.rickandmorty.core.viewmodel.ViewAction

sealed class CharactersViewAction : ViewAction {

    data class OpenCharacterDetails(val characterId: Long) : CharactersViewAction()
}