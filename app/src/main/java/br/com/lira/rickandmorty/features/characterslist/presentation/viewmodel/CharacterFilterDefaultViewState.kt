package br.com.lira.rickandmorty.features.characterslist.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import br.com.lira.rickandmorty.core.toolkit.SingleLiveData
import br.com.lira.rickandmorty.features.characterslist.domain.model.CharacterFilter
import javax.inject.Inject

class CharacterFilterDefaultViewState @Inject constructor() : CharacterFilterViewState {

    private val _filter = MutableLiveData<CharacterFilter>().apply { value = CharacterFilter() }
    private val _action = SingleLiveData<CharacterFilterViewAction>()
    private val _status = MutableLiveData<Int>()

    override val filter get() = _filter
    override val action get() = _action
    override val status get() = _status

    fun setFilter(block: (CharacterFilter?) -> CharacterFilter?) {
        _filter.value = block(_filter.value)
    }

    fun sendAction(action: CharacterFilterViewAction) {
        _action.value = action
    }

    fun setStatus(statusRes: Int) {
        _status.value = statusRes
    }
}
