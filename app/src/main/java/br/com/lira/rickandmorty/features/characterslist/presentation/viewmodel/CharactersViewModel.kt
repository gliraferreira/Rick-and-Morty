package br.com.lira.rickandmorty.features.characterslist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import br.com.lira.rickandmorty.features.characterslist.domain.usecase.GetAllCharactersUseCase
import br.com.lira.rickandmorty.features.characterslist.presentation.mapper.CharacterModelToUIMapper
import br.com.lira.rickandmorty.features.characterslist.presentation.view.CharactersListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getAllCharacters: GetAllCharactersUseCase,
    private val mutableState: CharactersDefaultViewState,
    private val characterUiMapper: CharacterModelToUIMapper
) : ViewModel(), CharactersListener {
    val viewState: CharactersViewState get() = mutableState

    init {
        mutableState.postState(CharactersViewState.State.LOADING)

        viewModelScope.launch {
            getAllCharacters().cachedIn(viewModelScope).collect { result ->
                val uiPagingData = result.map { characterUiMapper.mapFrom(it) }
                mutableState.postCharacters(uiPagingData)
                mutableState.postState(CharactersViewState.State.SUCCESS)
            }
        }
    }

    override fun onCharacterClicked(characterId: Long) {
        mutableState.sendAction(CharactersViewAction.OpenCharacterDetails(characterId))
    }
}