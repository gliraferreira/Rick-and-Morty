package br.com.lira.rickandmorty.features.locations.presentation.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import br.com.lira.rickandmorty.databinding.ListItemCharacterEpisodeBinding
import br.com.lira.rickandmorty.databinding.ListItemLocationBinding
import br.com.lira.rickandmorty.features.characters.presentation.model.CharacterEpisodeUIModel
import br.com.lira.rickandmorty.features.locations.presentation.model.LocationItemUI

class LocationsViewHolder(
    private val binding: ListItemLocationBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(location: LocationItemUI) {
        binding.locationName.text = location.name
        binding.locationType.text = location.type
    }
}
