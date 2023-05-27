package br.com.lira.rickandmorty.features.characterslist.presentation.viewmodel

import android.text.Editable
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import br.com.lira.rickandmorty.features.characterslist.domain.model.CharacterFilter
import br.com.lira.rickandmorty.features.characterslist.domain.usecase.GetAllCharactersUseCase
import br.com.lira.rickandmorty.features.characterslist.presentation.mapper.CharacterModelToUIMapper
import br.com.lira.rickandmorty.features.characterslist.presentation.view.CharactersListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val NAME_MIN_LENGTH = 3
private const val DELAY_INTERVAL = 500L

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getAllCharacters: GetAllCharactersUseCase,
    private val mutableState: CharactersDefaultViewState,
    private val characterUiMapper: CharacterModelToUIMapper
) : ViewModel(), CharactersListener {
    val viewState: CharactersViewState get() = mutableState
    private var searchJob: Job? = null

    init {
        viewModelScope.launch {
            loadCharacters()
        }
    }

    private suspend fun loadCharacters(name: String? = null) {
        setLoadingState()
        val filter = CharacterFilter(
            name = name
        )

        getAllCharacters(filter).cachedIn(viewModelScope).collect { result ->
            val uiPagingData = result.map { characterUiMapper.mapFrom(it) }
            mutableState.postCharacters(uiPagingData)
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

    fun onSearchBackClicked() {
        mutableState.postSearchStatus(false)
    }

    fun onSearchTextChanged(text: Editable?) {
        val name = text?.toString().orEmpty()

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            mutableState.postName(name)

            if (name.length > NAME_MIN_LENGTH) delay(DELAY_INTERVAL)

            loadCharacters(name)
        }
    }

    private fun setLoadingState() {
        mutableState.postCharacters(PagingData.empty())
        mutableState.postState(CharactersViewState.State.LOADING)
    }

    override fun onCharacterClicked(characterId: Long) {
        mutableState.sendAction(CharactersViewAction.OpenCharacterDetails(characterId))
    }
}