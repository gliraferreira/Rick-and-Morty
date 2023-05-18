package br.com.lira.rickandmorty.characterdetails.presentation.view.adapter

import androidx.recyclerview.widget.DiffUtil
import br.com.lira.rickandmorty.characterdetails.presentation.model.CharacterEpisodeUIModel

object CharacterEpisodeItemDiffCallback : DiffUtil.ItemCallback<CharacterEpisodeUIModel>() {

    override fun areItemsTheSame(
        oldItem: CharacterEpisodeUIModel,
        newItem: CharacterEpisodeUIModel
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: CharacterEpisodeUIModel,
        newItem: CharacterEpisodeUIModel
    ) = oldItem == newItem
}