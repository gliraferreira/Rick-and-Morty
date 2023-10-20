package br.com.lira.rickandmorty.features.locations.presentation.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import br.com.lira.rickandmorty.features.characters.presentation.model.CharacterEpisodeUIModel
import br.com.lira.rickandmorty.features.locations.presentation.model.LocationItemUI

object LocationItemUIDiffCallback : DiffUtil.ItemCallback<LocationItemUI>() {

    override fun areItemsTheSame(
        oldItem: LocationItemUI,
        newItem: LocationItemUI
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: LocationItemUI,
        newItem: LocationItemUI
    ) = oldItem == newItem
}