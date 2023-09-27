package br.com.lira.rickandmorty.features.characters.presentation.mapper

import br.com.lira.rickandmorty.features.characters.presentation.model.CharacterDetailsUIModel
import br.com.lira.rickandmorty.main.domain.model.Character
import javax.inject.Inject

class CharacterDetailsModelToUIMapper @Inject constructor(
    private val statusMapper: CharacterStatusMapper,
    private val statusColorMapper: CharacterStatusColorMapper,
    private val genderMapper: CharacterGenderMapper
) {

    fun mapFrom(character: Character) = CharacterDetailsUIModel(
        id = character.id,
        name = character.name,
        statusText = statusMapper.mapFrom(character.status),
        statusColor = statusColorMapper.mapFrom(character.status),
        species = character.species,
        gender = genderMapper.mapFrom(character.gender),
        image = character.image,
        lastLocation = character.location.name
    )
}
