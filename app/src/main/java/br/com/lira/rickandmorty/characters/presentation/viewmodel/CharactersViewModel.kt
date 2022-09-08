package br.com.lira.rickandmorty.characters.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.map
import br.com.lira.rickandmorty.characters.domain.usecase.GetAllCharactersUseCase
import br.com.lira.rickandmorty.characters.presentation.mapper.CharacterModelToUIMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getAllCharacters: GetAllCharactersUseCase,
    private val mutableState: CharactersDefaultViewState,
    private val characterUiMapper: CharacterModelToUIMapper
) : ViewModel() {

    val viewState: CharactersViewState get() = mutableState

    fun init() {
        mutableState.postState(CharactersViewState.State.LOADING)

        viewModelScope.launch {
            getAllCharacters().collect { result ->
                val uiPagingData = result.map { characterUiMapper.mapFrom(it) }
                mutableState.postCharacters(uiPagingData)
                mutableState.postState(CharactersViewState.State.SUCCESS)
            }
        }
    }
}