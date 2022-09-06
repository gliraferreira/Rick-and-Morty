package br.com.lira.rickandmorty.characters.presentation.view.adapter

import androidx.recyclerview.widget.DiffUtil
import br.com.lira.rickandmorty.characters.domain.model.Character

object CharacterItemDiffCallback : DiffUtil.ItemCallback<Character>() {

    override fun areItemsTheSame(oldItem: Character, newItem: Character) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Character, newItem: Character) = oldItem == newItem
}