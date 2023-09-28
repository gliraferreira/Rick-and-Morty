package br.com.lira.rickandmorty.features.locations.data.remote.mapper

import br.com.lira.rickandmorty.features.locations.data.remote.api.response.LocationResponse
import br.com.lira.rickandmorty.features.shared.domain.model.LocationShort
import javax.inject.Inject

class LocationResponseToShortModelMapper @Inject constructor() {

    fun mapFrom(response: LocationResponse) = LocationShort(
        id = response.id,
        name = response.name,
        type = response.type
    )
}