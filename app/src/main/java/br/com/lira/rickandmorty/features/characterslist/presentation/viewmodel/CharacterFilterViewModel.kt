package br.com.lira.rickandmorty.features.characterslist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import br.com.lira.rickandmorty.features.characterslist.domain.model.CharacterFilter
import br.com.lira.rickandmorty.features.characterslist.presentation.mapper.CharacterFilterUIModelMapper
import br.com.lira.rickandmorty.features.characterslist.presentation.mapper.CharacterStatusMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterFilterViewModel @Inject constructor(
    private val mutableState: CharacterFilterDefaultViewState,
    private val statusMapper: CharacterStatusMapper,
    private val filterUiMapper: CharacterFilterUIModelMapper
) : ViewModel() {

    val viewState: CharacterFilterViewState get() = mutableState

    fun init(currentFilter: CharacterFilter?) {
        mutableState.setFilter { currentFilter }
        mutableState.filter.value?.let(filterUiMapper::mapFrom)?.let { filterUi ->
            mutableState.sendAction(CharacterFilterViewAction.UpdateUI(filterUi))
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
