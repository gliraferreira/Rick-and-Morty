package br.com.lira.rickandmorty.features.shared.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import br.com.lira.rickandmorty.features.shared.presentation.model.CharacterUIModel

object CharacterItemDiffCallback : DiffUtil.ItemCallback<CharacterUIModel>() {

    override fun areItemsTheSame(
        oldItem: CharacterUIModel,
        newItem: CharacterUIModel
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: CharacterUIModel,
        newItem: CharacterUIModel
    ) = oldItem == newItem
}