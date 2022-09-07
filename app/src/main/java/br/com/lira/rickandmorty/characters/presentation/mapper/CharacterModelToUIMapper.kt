package br.com.lira.rickandmorty.characters.presentation.mapper

import br.com.lira.rickandmorty.R
import br.com.lira.rickandmorty.characters.domain.model.Character
import br.com.lira.rickandmorty.characters.domain.model.CharacterGender
import br.com.lira.rickandmorty.characters.domain.model.CharacterStatus
import br.com.lira.rickandmorty.characters.presentation.model.CharacterUIModel
import javax.inject.Inject

class CharacterModelToUIMapper @Inject constructor() {

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
    }

    private fun mapStatusColor(status: CharacterStatus) = when (status) {
        CharacterStatus.ALIVE -> R.color.character_status_alive
        CharacterStatus.DEAD -> R.color.character_status_dead
        else -> R.color.character_status_unknown
    }

    private fun mapGender(gender: CharacterGender) = when (gender) {
        CharacterGender.MALE -> R.string.character_gender_male
        CharacterGender.FEMALE -> R.string.character_gender_female
        else -> R.string.character_gender_unknown
    }
}