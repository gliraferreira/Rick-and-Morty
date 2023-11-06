package br.com.lira.rickandmorty.features.characters.presentation

import androidx.fragment.app.Fragment
import br.com.lira.rickandmorty.R
import br.com.lira.rickandmorty.core.toolkit.SlideFromRightAnimation
import br.com.lira.rickandmorty.core.toolkit.navigateToFragment
import br.com.lira.rickandmorty.features.characters.domain.model.CharacterFilter
import br.com.lira.rickandmorty.features.characters.presentation.ui.CharacterDetailsFragment
import br.com.lira.rickandmorty.features.characters.presentation.ui.CharacterFilterFragment
import javax.inject.Inject

class CharacterNavigator @Inject constructor() {

    fun openCharacterDetails(fragment: Fragment, characterId: Long) {
        fragment.navigateToFragment(
            hostRes = R.id.app_nav_host_fragment,
            destination = CharacterDetailsFragment.newInstance(characterId),
            fragmentAnimation = SlideFromRightAnimation
        )
    }

    fun openCharacterFilter(fragment: Fragment, currentFilter: CharacterFilter?) {
        fragment.navigateToFragment(
            hostRes = R.id.app_nav_host_fragment,
            destination = CharacterFilterFragment.newInstance(currentFilter),
            fragmentAnimation = SlideFromRightAnimation
        )
    }
}