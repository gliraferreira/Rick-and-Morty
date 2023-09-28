package br.com.lira.rickandmorty.core.toolkit

import androidx.fragment.app.FragmentTransaction
import br.com.lira.rickandmorty.R

object SlideFromRightAnimation : FragmentAnimation {

    override fun setupAnimation(transaction: FragmentTransaction) {
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
    }
}
