package br.com.lira.rickandmorty.characters.data.remote.mapper

import br.com.lira.rickandmorty.characters.data.remote.api.response.LocationResponse
import br.com.lira.rickandmorty.characters.domain.model.CharacterLocation
import br.com.lira.rickandmorty.core.mapper.UrlMapper
import javax.inject.Inject

class LocationResponseToModelMapper @Inject constructor(
    private val urlMapper: UrlMapper
) {

    fun mapFrom(response: LocationResponse) = CharacterLocation(
        name = response.name,
        id = urlMapper.mapId(response.url)
    )
}