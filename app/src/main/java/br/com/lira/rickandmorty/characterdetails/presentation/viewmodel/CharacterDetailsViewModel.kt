package br.com.lira.rickandmorty.characterdetails.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lira.rickandmorty.characterdetails.domain.usecase.GetCharacterByIdUseCase
import br.com.lira.rickandmorty.characterdetails.presentation.mapper.CharacterDetailsModelToUIMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val getCharacterById: GetCharacterByIdUseCase,
    private val mutableState: CharacterDetailsDefaultViewState,
    private val uiMapper: CharacterDetailsModelToUIMapper
) : ViewModel() {

    val viewState: CharacterDetailsViewState get() = mutableState

    fun init(characterId: Long?) {
        mutableState.postState(CharacterDetailsViewState.State.LOADING)

        viewModelScope.launch {
            runCatching {
                getCharacterById(characterId)
            }.onSuccess { character ->
                val uiModel = uiMapper.mapFrom(character)
                mutableState.postCharacter(uiModel)
                mutableState.postState(CharacterDetailsViewState.State.SUCCESS)
            }.onFailure { e ->
                Log.e("AppError", e.message.orEmpty())
            }
        }
    }
}