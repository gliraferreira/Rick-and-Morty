package br.com.lira.rickandmorty.characterdetails.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lira.rickandmorty.characterdetails.domain.usecase.GetCharacterByIdUseCase
import br.com.lira.rickandmorty.characterdetails.presentation.mapper.CharacterDetailsModelToUIMapper
import br.com.lira.rickandmorty.characterdetails.presentation.mapper.CharacterEpisodeUIModelMapper
import br.com.lira.rickandmorty.episodes.domain.usecase.GetMultipleEpisodesUseCase
import br.com.lira.rickandmorty.main.domain.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val getCharacterById: GetCharacterByIdUseCase,
    private val mutableState: CharacterDetailsDefaultViewState,
    private val uiMapper: CharacterDetailsModelToUIMapper,
    private val getMultipleEpisodes: GetMultipleEpisodesUseCase,
    private val episodeUiMapper: CharacterEpisodeUIModelMapper
) : ViewModel() {

    val viewState: CharacterDetailsViewState get() = mutableState

    fun init(characterId: Long?) {
        mutableState.postState(CharacterDetailsViewState.State.LOADING)

        viewModelScope.launch {
            runCatching {
                getCharacterById(characterId)
            }.onSuccess { character ->
                handleCharacterSuccess(character)
                fetchEpisodes(character.episodeIds)
            }.onFailure { e ->
                Log.e("AppError", e.message.orEmpty())
            }
        }
    }

    private fun handleCharacterSuccess(character: Character) {
        val uiModel = uiMapper.mapFrom(character)
        mutableState.postCharacter(uiModel)
        mutableState.postState(CharacterDetailsViewState.State.SUCCESS)
    }

    private suspend fun fetchEpisodes(episodeIds: List<String>) {
        runCatching {
            getMultipleEpisodes(episodeIds)
        }.onSuccess { episodes ->
            val episodesUi = episodes.map(episodeUiMapper::mapFrom)
            mutableState.postEpisodes(episodesUi)
        }
    }
}