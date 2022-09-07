package br.com.lira.rickandmorty.characters.presentation.view.adapter

import androidx.recyclerview.widget.RecyclerView
import br.com.lira.rickandmorty.characters.presentation.model.CharacterUIModel
import br.com.lira.rickandmorty.databinding.ListItemCharacterBinding

class CharacterListItemViewHolder(
    private val binding: ListItemCharacterBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(character: CharacterUIModel) {
        binding.character = character
    }
}