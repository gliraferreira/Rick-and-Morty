package br.com.lira.rickandmorty.features.locations.domain.usecase

import androidx.paging.PagingData
import br.com.lira.rickandmorty.features.episodes.domain.repository.EpisodesRepository
import br.com.lira.rickandmorty.features.locations.domain.repository.LocationsRepository
import br.com.lira.rickandmorty.features.shared.domain.model.LocationShort
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetAllLocationsUseCase @Inject constructor(
    private val repository: LocationsRepository
) {

    suspend operator fun invoke(): Flow<PagingData<LocationShort>> {
        return repository.getAllLocations()
    }
}
