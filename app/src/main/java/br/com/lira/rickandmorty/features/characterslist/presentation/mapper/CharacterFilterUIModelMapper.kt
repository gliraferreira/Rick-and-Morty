package br.com.lira.rickandmorty.features.characterslist.presentation.mapper

import br.com.lira.rickandmorty.R
import br.com.lira.rickandmorty.features.characterslist.domain.model.CharacterFilter
import br.com.lira.rickandmorty.features.characterslist.presentation.model.CharacterFilterUIModel
import br.com.lira.rickandmorty.main.domain.model.CharacterGender
import br.com.lira.rickandmorty.main.domain.model.CharacterStatus
import javax.inject.Inject

class CharacterFilterUIModelMapper @Inject constructor() {

    fun mapFrom(filter: CharacterFilter) = CharacterFilterUIModel(
        name = filter.name.orEmpty(),
        status = filter.status?.let(::mapStatus),
        gender = filter.gender?.let(::mapGender)
    )

    private fun mapStatus(status: CharacterStatus) = when (status) {
        CharacterStatus.ALIVE -> R.id.aliveStatusChip
        CharacterStatus.DEAD -> R.id.deadStatusChip
        else -> R.id.unknownStatusChip
    }

    private fun mapGender(gender: CharacterGender) = when (gender) {
        CharacterGender.FEMALE -> R.id.genderFemaleChip
        CharacterGender.MALE -> R.id.genderMaleChip
        CharacterGender.GENDERLESS -> R.id.genderGenderlessChip
        else -> R.id.genderUnknownChip
    }
}
