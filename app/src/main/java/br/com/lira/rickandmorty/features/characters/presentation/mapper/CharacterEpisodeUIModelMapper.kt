package br.com.lira.rickandmorty.features.characters.presentation.mapper

import br.com.lira.rickandmorty.R
import br.com.lira.rickandmorty.core.toolkit.ResourceProvider
import br.com.lira.rickandmorty.features.characters.presentation.model.CharacterEpisodeUIModel
import br.com.lira.rickandmorty.features.shared.domain.model.Episode
import javax.inject.Inject

class CharacterEpisodeUIModelMapper @Inject constructor(
    private val resourceProvider: ResourceProvider
) {

    fun mapFrom(episode: Episode) = CharacterEpisodeUIModel(
        id = episode.id,
        name = episode.name,
        episodeNumber = resourceProvider.getString(
            R.string.character_details_episode_number,
            episode.seasonNumber,
            episode.episodeNumber
        )
    )
}
