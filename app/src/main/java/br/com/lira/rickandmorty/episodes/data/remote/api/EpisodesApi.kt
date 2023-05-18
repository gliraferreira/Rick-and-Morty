package br.com.lira.rickandmorty.episodes.data.remote.api

import br.com.lira.rickandmorty.episodes.data.remote.api.response.EpisodeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodesApi {

    @GET("episode/{ids}")
    suspend fun getMultipleEpisodes(
        @Path("ids") episodeIds: List<String>
    ): List<EpisodeResponse>
}