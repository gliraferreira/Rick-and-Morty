package br.com.lira.rickandmorty.locations.presentation.mapper

import br.com.lira.rickandmorty.locations.domain.model.Location
import br.com.lira.rickandmorty.locations.presentation.model.LocationUIModel
import javax.inject.Inject

class LocationUIMapper @Inject constructor() {

    fun mapFrom(location: Location) = LocationUIModel(
        id = location.id,
        name = location.name,
        type = location.type,
        dimension = location.dimension
    )
}
