package br.com.lira.rickandmorty.characters.navigation

import androidx.fragment.app.Fragment
import br.com.lira.rickandmorty.characters.domain.model.CharacterFilter
import br.com.lira.rickandmorty.characters.presentation.ui.CharacterDetailsFragment
import br.com.lira.rickandmorty.characters.presentation.ui.CharacterFilterFragment
import br.com.lira.rickandmorty.navigation.NavigationHostProvider
import br.com.lira.rickandmorty.navigation.animation.SlideFromRightAnimation
import br.com.lira.rickandmorty.navigation.navigateToFragment
import javax.inject.Inject

class CharacterNavigatorImpl @Inject constructor(
    private val hostProvider: NavigationHostProvider
) : CharacterNavigator {

    override fun openCharacterDetails(fragment: Fragment, characterId: Long) {
        fragment.navigateToFragment(
            hostRes = hostProvider.getNavHostId(),
            destination = CharacterDetailsFragment.newInstance(characterId),
            fragmentAnimation = SlideFromRightAnimation
        )
    }

    override fun openCharacterFilter(fragment: Fragment, currentFilter: CharacterFilter?) {
        fragment.navigateToFragment(
            hostRes = hostProvider.getNavHostId(),
            destination = CharacterFilterFragment.newInstance(currentFilter),
            fragmentAnimation = SlideFromRightAnimation
        )
    }
}