package br.com.lira.rickandmorty.features.locations.domain.repository

import androidx.paging.PagingData
import br.com.lira.rickandmorty.features.shared.domain.model.LocationShort
import kotlinx.coroutines.flow.Flow

interface LocationsRepository {

    suspend fun getAllLocations(): Flow<PagingData<LocationShort>>
}
