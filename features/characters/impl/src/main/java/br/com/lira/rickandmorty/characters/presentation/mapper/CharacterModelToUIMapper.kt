package br.com.lira.rickandmorty.characters.presentation.mapper

import br.com.lira.rickandmorty.characters.domain.model.Character
import br.com.lira.rickandmorty.characters.presentation.model.CharacterUIModel
import javax.inject.Inject

class CharacterModelToUIMapper @Inject constructor(
    private val statusColorMapper: CharacterStatusColorMapper,
    private val genderMapper: CharacterGenderMapper
) {

    fun mapFrom(character: Character) = CharacterUIModel(
        id = character.id,
        name = character.name,
        statusColor = statusColorMapper.mapFrom(character.status),
        gender = genderMapper.mapFrom(character.gender),
        image = character.image
    )
}