package br.com.lira.rickandmorty.features.characterslist.presentation.model

import android.os.Parcelable
import br.com.lira.rickandmorty.main.domain.model.CharacterStatus
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterFilterUIModel(
    val name: String,
    val status: Int?,
    val gender: Int?
) : Parcelable
