package br.com.lira.rickandmorty.features.episodes.presentation.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.lira.rickandmorty.databinding.ListItemEpisodeBinding
import br.com.lira.rickandmorty.databinding.ListItemEpisodeHeaderBinding
import br.com.lira.rickandmorty.features.episodes.presentation.model.EpisodeUIModel

sealed class EpisodeListViewHolder(
    viewRoot: View
) : RecyclerView.ViewHolder(viewRoot) {

    class EpisodeItemViewHolder(
        private val binding: ListItemEpisodeBinding,
        private val onEpisodeClicked: (Long) -> Unit
    ) : EpisodeListViewHolder(binding.root) {

        fun bind(episode: EpisodeUIModel.Episode) {
            binding.root.setOnClickListener {
                onEpisodeClicked(episode.id)
            }
            binding.username.text = episode.name
            binding.gender.text = episode.seasonNumber
        }
    }

    class HeaderViewHolder(
        private val binding: ListItemEpisodeHeaderBinding
    ) : EpisodeListViewHolder(binding.root) {

        fun bind(episode: EpisodeUIModel.Header) {
            binding.username.text = episode.title
        }
    }
}
