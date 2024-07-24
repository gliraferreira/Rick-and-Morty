package br.lira.core.presentation.navigation

import androidx.fragment.app.FragmentTransaction
import br.lira.core.presentation.R

object SlideFromRightAnimation : FragmentAnimation {

    override fun setupAnimation(transaction: FragmentTransaction) {
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
    }
}
