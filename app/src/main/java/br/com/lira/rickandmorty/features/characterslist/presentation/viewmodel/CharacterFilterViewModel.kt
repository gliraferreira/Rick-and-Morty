package br.com.lira.rickandmorty.features.characterslist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import br.com.lira.rickandmorty.features.characterslist.domain.model.CharacterFilter
import br.com.lira.rickandmorty.features.characterslist.presentation.mapper.CharacterStatusMapper
import br.com.lira.rickandmorty.features.characterslist.presentation.mapper.CharacterStatusToResourceMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterFilterViewModel @Inject constructor(
    private val mutableState: CharacterFilterDefaultViewState,
    private val statusMapper: CharacterStatusMapper,
    private val statusResMapper: CharacterStatusToResourceMapper
) : ViewModel() {

    val viewState: CharacterFilterViewState get() = mutableState

    fun init(currentFilter: CharacterFilter?) {
        mutableState.setFilter { currentFilter }
        val filter = mutableState.filter.value
        filter?.status?.let(statusResMapper::mapFrom)?.let {
            mutableState.setStatus(it)
        }
    }

    fun onApplyFilterClicked(name: CharSequence) {
        mutableState.setFilter { it?.copy(name = name.toString()) }
        val filter = mutableState.filter.value
        mutableState.sendAction(CharacterFilterViewAction.SendFilterResult(filter))
    }

    fun onStatusChecked(ids: List<Int>) {
        val status = ids.firstOrNull()?.let(statusMapper::mapFrom)
        mutableState.setFilter { it?.copy(status = status) }
    }
}
