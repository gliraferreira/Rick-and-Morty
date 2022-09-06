package br.com.lira.rickandmorty.characters.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lira.rickandmorty.characters.domain.usecase.GetAllCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getAllCharacters: GetAllCharactersUseCase,
    private val mutableState: CharactersDefaultViewState
) : ViewModel() {

    val viewState: CharactersViewState get() = mutableState

    fun init() {
        mutableState.postState(CharactersViewState.State.LOADING)

        viewModelScope.launch {
            getAllCharacters()
                .onSuccess { characters ->
                    mutableState.postState(CharactersViewState.State.SUCCESS)
                    mutableState.postCharacters(characters)
                }
        }
    }
}