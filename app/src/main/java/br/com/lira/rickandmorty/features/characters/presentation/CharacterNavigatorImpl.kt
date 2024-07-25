package br.com.lira.rickandmorty.features.characters.presentation

import androidx.fragment.app.Fragment
import br.com.lira.rickandmorty.R
import br.com.lira.rickandmorty.characters.navigation.CharacterNavigator
import br.com.lira.rickandmorty.navigation.animation.SlideFromRightAnimation
import br.com.lira.rickandmorty.navigation.navigateToFragment
import br.com.lira.rickandmorty.characters.domain.model.CharacterFilter
import br.com.lira.rickandmorty.features.characters.presentation.ui.CharacterDetailsFragment
import br.com.lira.rickandmorty.features.characters.presentation.ui.CharacterFilterFragment
import javax.inject.Inject

class CharacterNavigatorImpl @Inject constructor() : CharacterNavigator {

    override fun openCharacterDetails(fragment: Fragment, characterId: Long) {
        fragment.navigateToFragment(
            hostRes = R.id.app_nav_host_fragment,
            destination = CharacterDetailsFragment.newInstance(characterId),
            fragmentAnimation = SlideFromRightAnimation
        )
    }

    override fun openCharacterFilter(fragment: Fragment, currentFilter: CharacterFilter?) {
        fragment.navigateToFragment(
            hostRes = R.id.app_nav_host_fragment,
            destination = CharacterFilterFragment.newInstance(currentFilter),
            fragmentAnimation = SlideFromRightAnimation
        )
    }
}