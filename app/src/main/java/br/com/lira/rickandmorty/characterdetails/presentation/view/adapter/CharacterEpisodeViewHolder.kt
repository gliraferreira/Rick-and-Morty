package br.com.lira.rickandmorty.characterdetails.presentation.view.adapter

import androidx.recyclerview.widget.RecyclerView
import br.com.lira.rickandmorty.characterdetails.presentation.model.CharacterEpisodeUIModel
import br.com.lira.rickandmorty.databinding.ListItemCharacterEpisodeBinding

class CharacterEpisodeViewHolder(
    private val binding: ListItemCharacterEpisodeBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(episode: CharacterEpisodeUIModel) {
        binding.episode = episode
    }
}