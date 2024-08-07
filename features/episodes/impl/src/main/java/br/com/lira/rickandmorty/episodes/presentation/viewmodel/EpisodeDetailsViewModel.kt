package br.com.lira.rickandmorty.episodes.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import br.com.lira.rickandmorty.characters.domain.model.CharacterShort
import br.com.lira.rickandmorty.characters.domain.usecase.GetMultipleCharactersUseCase
import br.com.lira.rickandmorty.characters.presentation.mapper.CharacterShortToUIMapper
import br.com.lira.rickandmorty.episodes.domain.model.Episode
import br.com.lira.rickandmorty.episodes.domain.usecase.GetEpisodeByIdUseCase
import br.com.lira.rickandmorty.episodes.impl.R
import br.com.lira.rickandmorty.episodes.presentation.mapper.EpisodeModelToDetailsUIMapper
import br.lira.core.presentation.ResourceProvider
import br.lira.core.presentation.mapper.GenericUIErrorMapper
import br.lira.core.presentation.viewmodel.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailsViewModel @Inject constructor(
    private val getEpisodeById: GetEpisodeByIdUseCase,
    private val getMultipleCharacters: GetMultipleCharactersUseCase,
    private val errorMapper: GenericUIErrorMapper,
    private val episodeMapper: EpisodeModelToDetailsUIMapper,
    private val characterMapper: CharacterShortToUIMapper,
    private val resourceProvider: ResourceProvider
) : ViewModel<EpisodeDetailsViewState, EpisodeDetailsViewAction>(EpisodeDetailsViewState()) {

    fun init(episodeId: Long?) {
        setState { it.setLoadingState() }

        viewModelScope.launch {
            runCatching { getEpisodeById(episodeId) }
                .onSuccess { handleEpisodeSuccess(it) }
                .onFailure { handleErrorState(it) }
        }
    }

    fun onCharacterClicked(characterId: Long) {
        sendAction { EpisodeDetailsViewAction.OpenCharacterDetails(characterId) }
    }

    private suspend fun handleEpisodeSuccess(episode: Episode) {
        val episodeUi = episodeMapper.mapFrom(episode)
        setState { it.setSuccessState(episodeUi) }
        fetchCharacters(episode.characterIds)
    }

    private suspend fun fetchCharacters(ids: List<String>) {
        setState { it.setCharactersLoadingState() }

        runCatching { getMultipleCharacters(ids) }
            .onSuccess { handleCharactersSuccess(it) }
            .onFailure { setState { it.setCharactersErrorState() } }
    }

    private fun handleCharactersSuccess(characters: List<CharacterShort>) {
        val charactersHeader = resourceProvider.getString(
            R.string.episode_details_characters_list_label,
            characters.size
        )
        val charactersUi = characters.map(characterMapper::mapFrom)
        setState { it.setCharactersSuccessState(charactersUi, charactersHeader) }
    }

    private fun handleErrorState(throwable: Throwable) {
        val error = errorMapper.mapFrom(throwable)
        setState { it.setErrorState(error) }
    }
}