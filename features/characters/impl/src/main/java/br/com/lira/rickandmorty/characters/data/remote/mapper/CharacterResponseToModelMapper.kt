package br.com.lira.rickandmorty.characters.data.remote.mapper

import br.com.lira.rickandmorty.core.data.mapper.UrlMapper
import br.com.lira.rickandmorty.core.data.utils.valueOf
import br.com.lira.rickandmorty.characters.data.remote.api.response.CharacterResponse
import br.com.lira.rickandmorty.characters.domain.model.Character
import br.com.lira.rickandmorty.characters.domain.model.CharacterGender
import br.com.lira.rickandmorty.characters.domain.model.CharacterStatus
import javax.inject.Inject

class CharacterResponseToModelMapper @Inject constructor(
    private val locationMapper: CharacterLocationResponseToModelMapper,
    private val urlMapper: UrlMapper
) {

    fun mapFrom(response: CharacterResponse) = Character(
        id = response.id,
        name = response.name,
        status = valueOf(response.status.uppercase(), CharacterStatus.UNKNOWN),
        species = response.species,
        type = response.type,
        gender = valueOf(response.gender.uppercase(), CharacterGender.UNKNOWN),
        origin = locationMapper.mapFrom(response.origin),
        location = locationMapper.mapFrom(response.location),
        image = response.image,
        episodeIds = response.episode.map(urlMapper::mapId)
    )
}
