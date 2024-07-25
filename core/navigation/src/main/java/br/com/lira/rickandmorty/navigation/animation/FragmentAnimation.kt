package br.com.lira.rickandmorty.navigation.animation

import androidx.fragment.app.FragmentTransaction

interface FragmentAnimation {

    fun setupAnimation(transaction: FragmentTransaction)
}
