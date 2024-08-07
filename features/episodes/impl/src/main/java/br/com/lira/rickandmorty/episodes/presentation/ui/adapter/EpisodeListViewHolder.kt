package br.com.lira.rickandmorty.episodes.presentation.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.lira.rickandmorty.episodes.impl.databinding.ListItemEpisodeBinding
import br.com.lira.rickandmorty.episodes.impl.databinding.ListItemEpisodeHeaderBinding
import br.com.lira.rickandmorty.episodes.presentation.model.EpisodeUIModel

sealed class EpisodeListViewHolder(
    viewRoot: View
) : RecyclerView.ViewHolder(viewRoot) {

    class EpisodeItemViewHolder(
        private val binding: ListItemEpisodeBinding,
        private val onEpisodeClicked: (Long) -> Unit
    ) : EpisodeListViewHolder(binding.root) {

        fun bind(episode: EpisodeUIModel.EpisodeUI) {
            binding.root.setOnClickListener {
                onEpisodeClicked(episode.id)
            }

            binding.episodeNumber.text = episode.formattedEpisodeNumber
            binding.episodeTitle.text = episode.name
            binding.episodeDetails.text = episode.airDate
        }
    }

    class HeaderViewHolder(
        private val binding: ListItemEpisodeHeaderBinding
    ) : EpisodeListViewHolder(binding.root) {

        fun bind(episode: EpisodeUIModel.Header) {
            binding.title.text = episode.title
        }
    }
}
