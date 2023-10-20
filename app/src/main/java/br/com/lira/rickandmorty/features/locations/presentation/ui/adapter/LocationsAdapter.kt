package br.com.lira.rickandmorty.features.locations.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import br.com.lira.rickandmorty.databinding.ListItemLocationBinding
import br.com.lira.rickandmorty.features.locations.presentation.model.LocationUIModel

class LocationsAdapter(
    private val onLocationClicked: (Long) -> Unit
) : PagingDataAdapter<LocationUIModel, LocationsViewHolder>(LocationItemUIDiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = LocationsViewHolder(
        binding = ListItemLocationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        onLocationClicked = onLocationClicked
    )

    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        getItem(position)?.let(holder::bind)
    }
}