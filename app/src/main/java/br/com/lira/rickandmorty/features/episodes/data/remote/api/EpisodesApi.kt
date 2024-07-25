package br.com.lira.rickandmorty.features.episodes.data.remote.api

import br.com.lira.rickandmorty.features.episodes.data.remote.api.response.EpisodeResponse
import br.com.lira.rickandmorty.core.data.response.PageResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EpisodesApi {

    @GET("episode/{ids}")
    suspend fun getMultipleEpisodes(
        @Path("ids") episodeIds: List<String>
    ): List<EpisodeResponse>

    @GET("episode")
    suspend fun getAllEpisodes(
        @Query("page") page: Int
    ): PageResponse<EpisodeResponse>

    @GET("episode/{id}")
    suspend fun getEpisodeById(
        @Path("id") episodeId: Long?
    ): EpisodeResponse
}
