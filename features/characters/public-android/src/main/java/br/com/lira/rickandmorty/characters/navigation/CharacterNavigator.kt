package br.com.lira.rickandmorty.characters.navigation

import androidx.fragment.app.Fragment
import br.com.lira.rickandmorty.characters.domain.model.CharacterFilter

interface CharacterNavigator {
    fun openCharacterDetails(fragment: Fragment, characterId: Long)

    fun openCharacterFilter(fragment: Fragment, currentFilter: CharacterFilter?)
}