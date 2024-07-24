package br.com.lira.rickandmorty.features.shared.presentation.adapter

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import br.com.lira.rickandmorty.databinding.ListItemCharacterBinding
import br.com.lira.rickandmorty.features.shared.presentation.model.CharacterUIModel
import br.lira.core.presentation.extensions.loadImage

class CharacterListItemViewHolder(
    private val binding: ListItemCharacterBinding,
    private val onCharacterClicked: (Long) -> Unit,
    private val isNavigationEnabled: Boolean = false
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(character: CharacterUIModel) {
        binding.navigationIcon.isVisible = isNavigationEnabled

        if (isNavigationEnabled) {
            binding.root.setOnClickListener {
                onCharacterClicked(character.id)
            }
        }

        binding.picture.loadImage(character.image)
        binding.username.text = character.name
        binding.gender.text = character.gender
        binding.picture.borderColor = character.statusColor
    }
}