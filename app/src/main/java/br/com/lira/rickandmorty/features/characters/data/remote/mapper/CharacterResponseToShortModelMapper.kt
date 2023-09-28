package br.com.lira.rickandmorty.features.characters.data.remote.mapper

import br.com.lira.rickandmorty.core.toolkit.valueOf
import br.com.lira.rickandmorty.features.characters.data.remote.api.response.CharacterResponse
import br.com.lira.rickandmorty.features.shared.domain.model.CharacterGender
import br.com.lira.rickandmorty.features.shared.domain.model.CharacterShort
import br.com.lira.rickandmorty.features.shared.domain.model.CharacterStatus
import javax.inject.Inject

class CharacterResponseToShortModelMapper @Inject constructor() {

    fun mapFrom(response: CharacterResponse) = CharacterShort(
        id = response.id,
        name = response.name,
        status = valueOf(response.status.uppercase(), CharacterStatus.UNKNOWN),
        gender = valueOf(response.gender.uppercase(), CharacterGender.UNKNOWN),
        image = response.image
    )
}
