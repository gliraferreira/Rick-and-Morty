package br.com.lira.rickandmorty.features.characters.presentation.mapper

import br.com.lira.rickandmorty.characters.domain.model.CharacterShort
import br.com.lira.rickandmorty.characters.presentation.mapper.CharacterShortToUIMapper
import br.com.lira.rickandmorty.characters.presentation.model.CharacterUIModel
import javax.inject.Inject

class CharacterShortToUIMapperImpl @Inject constructor(
    private val statusColorMapper: CharacterStatusColorMapper,
    private val genderMapper: CharacterGenderMapper
) : CharacterShortToUIMapper {

    override fun mapFrom(character: CharacterShort) = CharacterUIModel(
        id = character.id,
        name = character.name,
        statusColor = statusColorMapper.mapFrom(character.status),
        gender = genderMapper.mapFrom(character.gender),
        image = character.image
    )
}
