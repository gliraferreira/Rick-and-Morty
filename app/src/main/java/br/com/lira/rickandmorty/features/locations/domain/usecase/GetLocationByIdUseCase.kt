package br.com.lira.rickandmorty.features.locations.domain.usecase

import br.com.lira.rickandmorty.features.locations.domain.model.Location
import br.com.lira.rickandmorty.features.locations.domain.repository.LocationsRepository
import javax.inject.Inject

class GetLocationByIdUseCase @Inject constructor(
    private val repository: LocationsRepository
) {

    suspend operator fun invoke(locationId: Long?): Location {
        return repository.getLocationById(locationId)
    }
}
