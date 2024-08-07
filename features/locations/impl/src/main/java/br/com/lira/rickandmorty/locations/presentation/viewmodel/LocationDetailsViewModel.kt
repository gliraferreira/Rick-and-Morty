package br.com.lira.rickandmorty.locations.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import br.com.lira.rickandmorty.characters.domain.model.CharacterShort
import br.com.lira.rickandmorty.characters.domain.usecase.GetMultipleCharactersUseCase
import br.com.lira.rickandmorty.characters.presentation.mapper.CharacterShortToUIMapper
import br.com.lira.rickandmorty.locations.domain.model.Location
import br.com.lira.rickandmorty.locations.domain.usecase.GetLocationByIdUseCase
import br.com.lira.rickandmorty.locations.impl.R
import br.com.lira.rickandmorty.locations.presentation.mapper.LocationUIMapper
import br.lira.core.presentation.ResourceProvider
import br.lira.core.presentation.mapper.GenericUIErrorMapper
import br.lira.core.presentation.viewmodel.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationDetailsViewModel @Inject constructor(
    private val getLocationById: GetLocationByIdUseCase,
    private val getMultipleCharacters: GetMultipleCharactersUseCase,
    private val errorMapper: GenericUIErrorMapper,
    private val locationMapper: LocationUIMapper,
    private val characterMapper: CharacterShortToUIMapper,
    private val resourceProvider: ResourceProvider
) : ViewModel<LocationDetailsViewState, LocationDetailsViewAction>(LocationDetailsViewState()) {

    fun init(episodeId: Long?) {
        setState { it.setLoadingState() }

        viewModelScope.launch {
            runCatching { getLocationById(episodeId) }
                .onSuccess { handleEpisodeSuccess(it) }
                .onFailure { handleErrorState(it) }
        }
    }

    fun onCharacterClicked(characterId: Long) {
        sendAction { LocationDetailsViewAction.OpenCharacterDetails(characterId) }
    }

    private suspend fun handleEpisodeSuccess(location: Location) {
        val episodeUi = locationMapper.mapFrom(location)
        setState { it.setSuccessState(episodeUi) }
        fetchCharacters(location.residentIds)
    }

    private suspend fun fetchCharacters(ids: List<String>) {
        setState { it.setCharactersLoadingState() }

        runCatching { getMultipleCharacters(ids) }
            .onSuccess { handleCharactersSuccess(it) }
            .onFailure { handleCharactersError() }
    }

    private fun handleCharactersSuccess(characters: List<CharacterShort>) {
        val charactersHeader = resourceProvider.getString(
            R.string.location_details_characters_list_label,
            characters.size
        )
        val charactersUi = characters.map(characterMapper::mapFrom)
        setState { it.setCharactersSuccessState(charactersUi, charactersHeader) }
    }

    private fun handleCharactersError() {
        val charactersHeader = resourceProvider.getString(
            R.string.location_details_empty_characters_list_label
        )
        setState { it.setCharactersErrorState(charactersHeader) }
    }

    private fun handleErrorState(throwable: Throwable) {
        val error = errorMapper.mapFrom(throwable)
        setState { it.setErrorState(error) }
    }
}