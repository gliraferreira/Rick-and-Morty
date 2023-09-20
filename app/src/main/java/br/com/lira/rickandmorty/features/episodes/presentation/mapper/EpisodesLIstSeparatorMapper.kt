package br.com.lira.rickandmorty.features.episodes.presentation.mapper

import androidx.paging.PagingData
import androidx.paging.insertSeparators
import br.com.lira.rickandmorty.features.episodes.presentation.model.EpisodeUIModel
import javax.inject.Inject

class EpisodesLIstSeparatorMapper @Inject constructor() {

    fun mapFrom(
        pagingData: PagingData<EpisodeUIModel.EpisodeUI>
    ) = pagingData.insertSeparators { before, after ->
        if (after == null) {
            return@insertSeparators null
        }

        if (before == null || before.seasonNumber != after.seasonNumber) {
            return@insertSeparators EpisodeUIModel.Header(after.seasonNumber)
        }

        return@insertSeparators null
    }
}
