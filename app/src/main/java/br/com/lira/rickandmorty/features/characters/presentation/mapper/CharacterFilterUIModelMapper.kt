package br.com.lira.rickandmorty.features.characters.presentation.mapper

import br.com.lira.rickandmorty.R
import br.com.lira.rickandmorty.features.characters.domain.model.CharacterFilter
import br.com.lira.rickandmorty.features.characters.presentation.model.CharacterFilterUIModel
import br.com.lira.rickandmorty.main.domain.model.CharacterGender
import br.com.lira.rickandmorty.main.domain.model.CharacterStatus
import javax.inject.Inject

class CharacterFilterUIModelMapper @Inject constructor() {

    fun mapFrom(filter: CharacterFilter) = CharacterFilterUIModel(
        name = filter.name.orEmpty(),
        selectedStatus = filter.status?.let(::mapStatusChip),
        selectedGender = filter.gender?.let(::mapGenderChip)
    )

    private fun mapStatusChip(status: CharacterStatus) = when (status) {
        CharacterStatus.ALIVE -> R.id.aliveStatusChip
        CharacterStatus.DEAD -> R.id.deadStatusChip
        else -> R.id.unknownStatusChip
    }

    private fun mapGenderChip(gender: CharacterGender) = when (gender) {
        CharacterGender.FEMALE -> R.id.genderFemaleChip
        CharacterGender.MALE -> R.id.genderMaleChip
        CharacterGender.GENDERLESS -> R.id.genderGenderlessChip
        else -> R.id.genderUnknownChip
    }
}
