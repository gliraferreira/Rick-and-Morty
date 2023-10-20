package br.com.lira.rickandmorty.features.locations.data.remote.mapper

import br.com.lira.rickandmorty.core.mapper.UrlMapper
import br.com.lira.rickandmorty.features.locations.data.remote.api.response.LocationResponse
import br.com.lira.rickandmorty.features.locations.domain.model.Location
import br.com.lira.rickandmorty.features.shared.domain.model.LocationShort
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