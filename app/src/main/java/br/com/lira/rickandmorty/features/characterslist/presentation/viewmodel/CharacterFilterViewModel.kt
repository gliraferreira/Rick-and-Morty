package br.com.lira.rickandmorty.features.characterslist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterFilterViewModel @Inject constructor(
    private val mutableState: CharacterFilterDefaultViewState
) : ViewModel() {

    val viewState: CharacterFilterViewState get() = mutableState
}