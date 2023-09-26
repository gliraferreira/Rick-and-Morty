package br.com.lira.rickandmorty.features.episodes.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import br.com.lira.rickandmorty.databinding.ListItemEpisodeBinding
import br.com.lira.rickandmorty.databinding.ListItemEpisodeHeaderBinding
import br.com.lira.rickandmorty.features.episodes.presentation.model.EpisodeUIModel

private const val EPISODE_ITEM_TYPE = 0
private const val HEADER_ITEM_TYPE = 1

class EpisodesListAdapter(
    private val onCharacterClicked: (Long) -> Unit
) : PagingDataAdapter<EpisodeUIModel, EpisodeListViewHolder>(EpisodeItemDiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EpisodeListViewHolder {

        return when (viewType) {
            EPISODE_ITEM_TYPE -> EpisodeListViewHolder.EpisodeItemViewHolder(
                binding = ListItemEpisodeBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                onEpisodeClicked = onCharacterClicked
            )

            else -> EpisodeListViewHolder.HeaderViewHolder(
                binding = ListItemEpisodeHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: EpisodeListViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is EpisodeListViewHolder.EpisodeItemViewHolder -> {
                val episode = item as? EpisodeUIModel.EpisodeUI
                episode?.let(holder::bind)
            }

            is EpisodeListViewHolder.HeaderViewHolder -> {
                val episode = item as? EpisodeUIModel.Header
                episode?.let(holder::bind)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is EpisodeUIModel.EpisodeUI -> EPISODE_ITEM_TYPE
            is EpisodeUIModel.Header -> HEADER_ITEM_TYPE
            else -> super.getItemViewType(position)
        }
    }
}
