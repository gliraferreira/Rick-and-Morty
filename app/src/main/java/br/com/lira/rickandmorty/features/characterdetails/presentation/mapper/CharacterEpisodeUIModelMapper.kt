package br.com.lira.rickandmorty.features.characterdetails.presentation.mapper

import br.com.lira.rickandmorty.features.characterdetails.presentation.model.CharacterEpisodeUIModel
import br.com.lira.rickandmorty.main.domain.model.Episode
import javax.inject.Inject

class CharacterEpisodeUIModelMapper @Inject constructor() {

    fun mapFrom(episode: Episode) = CharacterEpisodeUIModel(
        id = episode.id,
        name = episode.name,
        episode = episode.episode
    )
}
