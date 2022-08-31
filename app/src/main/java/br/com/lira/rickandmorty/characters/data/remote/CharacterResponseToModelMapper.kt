package br.com.lira.rickandmorty.characters.data.remote

import br.com.lira.rickandmorty.characters.data.remote.api.response.CharacterResponse
import br.com.lira.rickandmorty.characters.domain.model.Character
import javax.inject.Inject

class CharacterResponseToModelMapper @Inject constructor() {

    fun mapFrom(response: CharacterResponse): Character {
        TODO()
    }
}