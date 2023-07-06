package br.com.lira.rickandmorty.features.characterslist.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import br.com.lira.rickandmorty.features.characterslist.domain.model.CharacterFilter
import javax.inject.Inject

class CharacterFilterDefaultViewState @Inject constructor() : CharacterFilterViewState {

    private val _filter = MutableLiveData<CharacterFilter>().apply { value = CharacterFilter() }

    override val filter get() = _filter
}