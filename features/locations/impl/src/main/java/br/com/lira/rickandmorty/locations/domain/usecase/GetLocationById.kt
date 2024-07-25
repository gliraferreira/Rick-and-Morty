package br.com.lira.rickandmorty.locations.domain.usecase

import br.com.lira.rickandmorty.locations.domain.model.Location
import br.com.lira.rickandmorty.locations.domain.repository.LocationsRepository
import javax.inject.Inject

class GetLocationById @Inject constructor(
    private val repository: LocationsRepository
) : GetLocationByIdUseCase {

    override suspend operator fun invoke(locationId: Long?): Location {
        return repository.getLocationById(locationId)
    }
}
