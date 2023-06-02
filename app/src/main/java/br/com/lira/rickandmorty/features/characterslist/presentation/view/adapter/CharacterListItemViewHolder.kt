package br.com.lira.rickandmorty.features.characterslist.presentation.view.adapter

import androidx.recyclerview.widget.RecyclerView
import br.com.lira.rickandmorty.databinding.ListItemCharacterBinding
import br.com.lira.rickandmorty.features.characterslist.presentation.model.CharacterUIModel

class CharacterListItemViewHolder(
    private val binding: ListItemCharacterBinding,
    private val onCharacterClicked: (Long) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(character: CharacterUIModel) {
        binding.character = character
        binding.content.setOnClickListener {
            onCharacterClicked(character.id)
        }
    }
}