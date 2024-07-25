package br.com.lira.rickandmorty.locations.domain.usecase

import androidx.paging.PagingData
import br.com.lira.rickandmorty.locations.domain.model.LocationShort
import kotlinx.coroutines.flow.Flow

interface GetAllLocationsUseCase {
    suspend operator fun invoke(): Flow<PagingData<LocationShort>>
}