package br.com.lira.rickandmorty.features.characterslist.presentation.mapper

import br.com.lira.rickandmorty.R
import br.com.lira.rickandmorty.core.toolkit.ResourceProvider
import br.com.lira.rickandmorty.main.domain.model.Character
import br.com.lira.rickandmorty.main.domain.model.CharacterGender
import br.com.lira.rickandmorty.main.domain.model.CharacterStatus
import br.com.lira.rickandmorty.features.characterslist.presentation.model.CharacterUIModel
import javax.inject.Inject

class CharacterModelToUIMapper @Inject constructor(
    private val resourceProvider: ResourceProvider
) {

    fun mapFrom(character: Character) = CharacterUIModel(
        id = character.id,
        name = character.name,
        statusText = mapStatusText(character.status),
        statusColor = mapStatusColor(character.status),
        species = character.species,
        type = character.type,
        gender = mapGender(character.gender),
        image = character.image
    )

    private fun mapStatusText(status: CharacterStatus) = when (status) {
        CharacterStatus.ALIVE -> R.string.character_status_alive
        CharacterStatus.DEAD -> R.string.character_status_dead
        else -> R.string.character_status_unknown
    }.let(resourceProvider::getString)

    private fun mapStatusColor(status: CharacterStatus) = when (status) {
        CharacterStatus.ALIVE -> R.color.character_status_alive
        CharacterStatus.DEAD -> R.color.character_status_dead
        else -> R.color.character_status_unknown
    }.let(resourceProvider::getColor)

    private fun mapGender(gender: CharacterGender) = when (gender) {
        CharacterGender.MALE -> R.string.character_gender_male
        CharacterGender.FEMALE -> R.string.character_gender_female
        CharacterGender.GENDERLESS -> R.string.character_gender_genderless
        else -> R.string.character_gender_unknown
    }.let(resourceProvider::getString)
}