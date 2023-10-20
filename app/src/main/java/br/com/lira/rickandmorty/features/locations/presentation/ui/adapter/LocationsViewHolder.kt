package br.com.lira.rickandmorty.features.locations.presentation.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import br.com.lira.rickandmorty.databinding.ListItemLocationBinding
import br.com.lira.rickandmorty.features.locations.presentation.model.LocationUIModel

class LocationsViewHolder(
    private val binding: ListItemLocationBinding,
    private val onLocationClicked: (Long) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(location: LocationUIModel) {
        binding.locationName.text = location.name
        binding.locationType.text = location.type
        binding.root.setOnClickListener {
            onLocationClicked(location.id)
        }
    }
}
