package br.com.lira.rickandmorty.features.characterdetails.domain.repository

import br.com.lira.rickandmorty.main.domain.model.Character

interface CharacterDetailsRepository {

    suspend fun getCharacter(characterId: Long?): Character
}