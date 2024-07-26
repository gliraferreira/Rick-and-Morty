package br.com.lira.rickandmorty.characters.presentation.viewmodel

import br.lira.core.presentation.viewmodel.ViewModel
import br.com.lira.rickandmorty.characters.domain.model.CharacterFilter
import br.com.lira.rickandmorty.characters.presentation.mapper.CharacterFilterUIModelMapper
import br.com.lira.rickandmorty.characters.presentation.mapper.CharacterFilterGenderMapper
import br.com.lira.rickandmorty.characters.presentation.mapper.CharacterFilterStatusMapper
import br.com.lira.rickandmorty.characters.presentation.viewaction.CharacterFilterViewAction
import br.com.lira.rickandmorty.characters.presentation.viewstate.CharacterFilterViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterFilterViewModel @Inject constructor(
    private val statusMapper: CharacterFilterStatusMapper,
    private val filterUiMapper: CharacterFilterUIModelMapper,
    private val genderMapper: CharacterFilterGenderMapper
) : ViewModel<CharacterFilterViewState, CharacterFilterViewAction>(CharacterFilterViewState()) {

    fun init(currentFilter: CharacterFilter?) {
        currentFilter?.let(filterUiMapper::mapFrom)?.let {
            sendAction { CharacterFilterViewAction.UpdateUI(it) }
        }
        setState { it.copy(filter = currentFilter) }
    }

    fun onApplyFilterClicked(name: CharSequence) {
        val filter = state.value?.filter?.copy(name = name.toString())
        sendAction { CharacterFilterViewAction.SendFilterResult(filter) }
    }

    fun onStatusChecked(ids: List<Int>) {
        val status = ids.firstOrNull()?.let(statusMapper::mapFrom)
        setState { it.updateStatus(status) }
    }

    fun onGenderChecked(ids: List<Int>) {
        val gender = ids.firstOrNull()?.let(genderMapper::mapFrom)
        setState { it.updateGender(gender) }
    }
}
