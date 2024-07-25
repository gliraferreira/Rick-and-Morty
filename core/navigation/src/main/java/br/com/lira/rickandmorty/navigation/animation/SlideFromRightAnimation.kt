package br.com.lira.rickandmorty.navigation.animation

import androidx.fragment.app.FragmentTransaction
import br.com.lira.rickandmorty.navigation.R

object SlideFromRightAnimation : FragmentAnimation {

    override fun setupAnimation(transaction: FragmentTransaction) {
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
    }
}
