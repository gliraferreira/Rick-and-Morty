package br.com.lira.rickandmorty.locations.domain.repository

import androidx.paging.PagingData
import br.com.lira.rickandmorty.locations.domain.model.Location
import br.com.lira.rickandmorty.locations.domain.model.LocationShort
import kotlinx.coroutines.flow.Flow

interface LocationsRepository {

    suspend fun getAllLocations(): Flow<PagingData<LocationShort>>

    suspend fun getLocationById(locationId: Long?): Location
}
