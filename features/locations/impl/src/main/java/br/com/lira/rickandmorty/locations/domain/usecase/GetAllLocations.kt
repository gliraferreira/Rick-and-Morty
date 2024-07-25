package br.com.lira.rickandmorty.locations.domain.usecase

import androidx.paging.PagingData
import br.com.lira.rickandmorty.locations.domain.model.LocationShort
import br.com.lira.rickandmorty.locations.domain.repository.LocationsRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetAllLocations @Inject constructor(
    private val repository: LocationsRepository
) : GetAllLocationsUseCase {

    override suspend operator fun invoke(): Flow<PagingData<LocationShort>> {
        return repository.getAllLocations()
    }
}
