package br.com.lira.rickandmorty.features.characters.presentation.mapper

import br.com.lira.rickandmorty.R
import br.com.lira.rickandmorty.features.characters.presentation.model.CharacterDetailsUIModel
import br.com.lira.rickandmorty.main.domain.model.Character
import br.com.lira.rickandmorty.main.domain.model.CharacterGender
import br.com.lira.rickandmorty.main.domain.model.CharacterStatus
import javax.inject.Inject

class CharacterDetailsModelToUIMapper @Inject constructor() {

    fun mapFrom(character: Character) = CharacterDetailsUIModel(
        id = character.id,
        name = character.name,
        statusText = mapStatusText(character.status),
        statusColor = mapStatusColor(character.status),
        species = character.species,
        gender = mapGender(character.gender),
        image = character.image,
        lastLocation = character.location.name
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