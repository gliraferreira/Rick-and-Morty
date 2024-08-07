package br.com.lira.rickandmorty.characters.presentation.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import br.com.lira.rickandmorty.characters.impl.databinding.ListItemCharacterEpisodeBinding
import br.com.lira.rickandmorty.characters.presentation.model.CharacterEpisodeUIModel

class CharacterEpisodeViewHolder(
    private val binding: ListItemCharacterEpisodeBinding,
    private val onEpisodeClicked: (Long) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(episode: CharacterEpisodeUIModel) {
        binding.tvName.text = episode.name
        binding.tvEpisodeNumber.text = episode.episodeNumber
        binding.root.setOnClickListener {
            onEpisodeClicked(episode.id)
        }
    }
}
