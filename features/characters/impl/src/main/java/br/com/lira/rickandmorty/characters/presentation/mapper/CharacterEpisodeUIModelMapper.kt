package br.com.lira.rickandmorty.characters.presentation.mapper

import br.com.lira.rickandmorty.characters.impl.R
import br.com.lira.rickandmorty.characters.presentation.model.CharacterEpisodeUIModel
import br.com.lira.rickandmorty.episodes.domain.model.Episode
import br.lira.core.presentation.ResourceProvider
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