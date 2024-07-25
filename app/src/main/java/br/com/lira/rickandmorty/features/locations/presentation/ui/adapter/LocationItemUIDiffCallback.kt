package br.com.lira.rickandmorty.features.locations.presentation.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import br.com.lira.rickandmorty.locations.presentation.model.LocationUIModel

object LocationItemUIDiffCallback : DiffUtil.ItemCallback<LocationUIModel>() {

    override fun areItemsTheSame(
        oldItem: LocationUIModel,
        newItem: LocationUIModel
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: LocationUIModel,
        newItem: LocationUIModel
    ) = oldItem == newItem
}