package br.com.lira.rickandmorty.features.characters.data.remote.mapper

import br.com.lira.rickandmorty.features.characters.data.remote.api.response.CharacterLocationResponse
import br.com.lira.rickandmorty.features.shared.domain.model.CharacterLocation
import br.com.lira.rickandmorty.core.mapper.UrlMapper
import javax.inject.Inject

class CharacterLocationResponseToModelMapper @Inject constructor(
    private val urlMapper: UrlMapper
) {

    fun mapFrom(response: CharacterLocationResponse) = CharacterLocation(
        name = response.name,
        id = urlMapper.mapId(response.url)
    )
}
