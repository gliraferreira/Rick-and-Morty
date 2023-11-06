package br.com.lira.rickandmorty.features.characters.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import br.com.lira.rickandmorty.R
import br.com.lira.rickandmorty.core.toolkit.ResourceProvider
import br.com.lira.rickandmorty.core.viewmodel.ViewModel
import br.com.lira.rickandmorty.features.characters.domain.usecase.GetCharacterByIdUseCase
import br.com.lira.rickandmorty.features.characters.presentation.mapper.CharacterDetailsModelToUIMapper
import br.com.lira.rickandmorty.features.characters.presentation.mapper.CharacterEpisodeUIModelMapper
import br.com.lira.rickandmorty.features.characters.presentation.viewaction.CharacterDetailsViewAction
import br.com.lira.rickandmorty.features.characters.presentation.viewstate.CharacterDetailsViewState
import br.com.lira.rickandmorty.features.episodes.domain.usecase.GetMultipleEpisodesUseCase
import br.com.lira.rickandmorty.features.characters.domain.model.Character
import br.com.lira.rickandmorty.features.shared.domain.model.CharacterLocation
import br.com.lira.rickandmorty.features.shared.domain.model.Episode
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val getCharacterById: GetCharacterByIdUseCase,
    private val uiMapper: CharacterDetailsModelToUIMapper,
    private val getMultipleEpisodes: GetMultipleEpisodesUseCase,
    private val episodeUiMapper: CharacterEpisodeUIModelMapper,
    private val resourceProvider: ResourceProvider
) : ViewModel<CharacterDetailsViewState, CharacterDetailsViewAction>(CharacterDetailsViewState()) {

    private var currentLocation: CharacterLocation? = null

    fun init(characterId: Long?) {
        setState { it.setLoadingState() }

        viewModelScope.launch {
            runCatching { getCharacterById(characterId) }
                .onSuccess { handleCharacterSuccess(it) }
        }
    }

    fun onEpisodeClicked(episodeId: Long) {
        sendAction { CharacterDetailsViewAction.OpenEpisodeDetails(episodeId) }
    }

    fun onCurrentLocationClicked() {
        currentLocation?.let {
            sendAction { CharacterDetailsViewAction.OpenCurrentLocationDetails(it.id.toLong()) }
        }
    }

    private suspend fun handleCharacterSuccess(character: Character) {
        val itContainsLocationId = character.location.id.isNotBlank()
        currentLocation = character.location.takeIf { itContainsLocationId }

        val uiModel = uiMapper.mapFrom(character)
        setState { it.setSuccessState(uiModel, itContainsLocationId) }

        fetchEpisodes(character.episodeIds)
    }

    private suspend fun fetchEpisodes(episodeIds: List<String>) {
        setState { it.setEpisodesLoadingState() }

        runCatching { getMultipleEpisodes(episodeIds) }
            .onSuccess { handleEpisodesSuccess(it) }
            .onFailure { setState { it.setEpisodesErrorState() } }
    }

    private fun handleEpisodesSuccess(episodes: List<Episode>) {
        val header = resourceProvider.getString(
            R.string.character_details_episodes_title,
            episodes.size
        )
        val episodesUi = episodes.map(episodeUiMapper::mapFrom)
        setState { it.setEpisodesSuccessState(episodes = episodesUi, header = header) }
    }
}