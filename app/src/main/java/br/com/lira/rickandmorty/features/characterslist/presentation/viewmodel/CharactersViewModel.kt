package br.com.lira.rickandmorty.features.characterslist.presentation.viewmodel

import android.text.Editable
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import br.com.lira.rickandmorty.features.characterslist.domain.usecase.GetAllCharactersUseCase
import br.com.lira.rickandmorty.features.characterslist.domain.usecase.SearchCharactersUseCase
import br.com.lira.rickandmorty.features.characterslist.presentation.mapper.CharacterModelToUIMapper
import br.com.lira.rickandmorty.features.characterslist.presentation.view.CharactersListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getAllCharacters: GetAllCharactersUseCase,
    private val mutableState: CharactersDefaultViewState,
    private val characterUiMapper: CharacterModelToUIMapper,
    private val searchCharacters: SearchCharactersUseCase
) : ViewModel(), CharactersListener {
    val viewState: CharactersViewState get() = mutableState
    private var searchJob: Job? = null

    init {
        viewModelScope.launch {
            mutableState.postState(CharactersViewState.State.LOADING)

            getAllCharacters().cachedIn(viewModelScope).catch { cause ->
                Log.e("RickError", cause.message.orEmpty())
            }.collect { result ->
                val uiPagingData = result.map { characterUiMapper.mapFrom(it) }
                mutableState.postCharacters(uiPagingData)
            }
        }
    }

    fun onCharactersListSubmitted() {
        mutableState.postState(CharactersViewState.State.SUCCESS)
    }

    fun onSearchClicked() {
        mutableState.postSearchStatus(true)
    }

    fun onSearchFocusChanged(hasFocus: Boolean) {
        mutableState.postSearchStatus(hasFocus)
    }

    fun onSearchTextChanged(text: Editable?) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            mutableState.postName(text.toString())
            if (text?.toString().orEmpty().length > 3) delay(500)

            mutableState.postCharacters(PagingData.empty())
            mutableState.postState(CharactersViewState.State.LOADING)
            searchCharacters(text.toString()).cachedIn(viewModelScope).catch { cause ->
                Log.e("AppError", cause.message.orEmpty())
            }.collect { result ->
                val uiPagingData = result.map { characterUiMapper.mapFrom(it) }
                mutableState.postCharacters(uiPagingData)
            }
        }
    }

    override fun onCharacterClicked(characterId: Long) {
        mutableState.sendAction(CharactersViewAction.OpenCharacterDetails(characterId))
    }
}