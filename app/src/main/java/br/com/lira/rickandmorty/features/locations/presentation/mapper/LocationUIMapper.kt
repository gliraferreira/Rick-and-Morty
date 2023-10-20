package br.com.lira.rickandmorty.features.locations.presentation.mapper

import br.com.lira.rickandmorty.features.locations.domain.model.Location
import br.com.lira.rickandmorty.features.locations.presentation.model.LocationUIModel
import br.com.lira.rickandmorty.features.shared.domain.model.LocationShort
import javax.inject.Inject

class LocationUIMapper @Inject constructor() {

    fun mapFrom(location: Location) = LocationUIModel(
        id = location.id,
        name = location.name,
        type = location.type,
        dimension = location.dimension
    )
}
