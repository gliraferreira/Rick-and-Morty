package br.com.lira.rickandmorty.features.characterdetails.presentation.view.adapter

import androidx.recyclerview.widget.RecyclerView
import br.com.lira.rickandmorty.databinding.ListItemCharacterEpisodeBinding
import br.com.lira.rickandmorty.features.characterdetails.presentation.model.CharacterEpisodeUIModel

class CharacterEpisodeViewHolder(
    private val binding: ListItemCharacterEpisodeBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(episode: CharacterEpisodeUIModel) {
        binding.tvName.text = episode.name
        binding.tvEpisodeNumber.text = episode.episodeNumber
    }
}
