package br.com.lira.rickandmorty.locations.data.remote.mapper

import br.com.lira.rickandmorty.core.data.mapper.UrlMapper
import br.com.lira.rickandmorty.locations.data.remote.api.response.LocationResponse
import br.com.lira.rickandmorty.locations.domain.model.Location
import javax.inject.Inject

class LocationResponseToModelMapper @Inject constructor(
    private val urlMapper: UrlMapper
) {

    fun mapFrom(response: LocationResponse) = Location(
        id = response.id,
        name = response.name,
        type = response.type,
        dimension = response.dimension,
        residentIds = response.residents.map(urlMapper::mapId)
    )
}