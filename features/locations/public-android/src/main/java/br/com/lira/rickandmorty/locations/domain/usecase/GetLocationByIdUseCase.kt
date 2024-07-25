package br.com.lira.rickandmorty.locations.domain.usecase

import br.com.lira.rickandmorty.locations.domain.model.Location

interface GetLocationByIdUseCase {
    suspend operator fun invoke(locationId: Long?): Location
}