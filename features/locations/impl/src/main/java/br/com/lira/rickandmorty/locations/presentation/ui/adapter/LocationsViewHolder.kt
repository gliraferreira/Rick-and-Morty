package br.com.lira.rickandmorty.locations.presentation.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import br.com.lira.rickandmorty.locations.impl.databinding.ListItemLocationBinding
import br.com.lira.rickandmorty.locations.presentation.model.LocationUIModel

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
