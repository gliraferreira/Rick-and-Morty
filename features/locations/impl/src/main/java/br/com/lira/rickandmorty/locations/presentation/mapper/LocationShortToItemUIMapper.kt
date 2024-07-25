package br.com.lira.rickandmorty.locations.presentation.mapper

import br.com.lira.rickandmorty.locations.presentation.model.LocationUIModel
import br.com.lira.rickandmorty.locations.domain.model.LocationShort
import javax.inject.Inject

class LocationShortToItemUIMapper @Inject constructor() {

    fun mapFrom(location: LocationShort) = LocationUIModel(
        id = location.id,
        name = location.name,
        type = location.type,
        dimension = null
    )
}
