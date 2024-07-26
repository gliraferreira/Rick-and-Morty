package br.com.lira.rickandmorty.episodes.presentation.mapper

import androidx.paging.PagingData
import androidx.paging.insertSeparators
import br.com.lira.rickandmorty.episodes.presentation.model.EpisodeUIModel
import javax.inject.Inject

class EpisodesListSeparatorMapper @Inject constructor() {

    fun mapFrom(
        pagingData: PagingData<EpisodeUIModel.EpisodeUI>
    ) = pagingData.insertSeparators { before, after ->
        if (after == null) {
            return@insertSeparators null
        }

        if (before == null || before.formattedSeasonNumber != after.formattedSeasonNumber) {
            return@insertSeparators EpisodeUIModel.Header(after.formattedSeasonNumber)
        }

        return@insertSeparators null
    }
}
