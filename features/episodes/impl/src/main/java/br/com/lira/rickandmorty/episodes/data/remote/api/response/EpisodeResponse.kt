package br.com.lira.rickandmorty.episodes.data.remote.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EpisodeResponse (
    val id: Long,
    val name: String,
    @Json(name = "air_date")
    val airDate: String,
    @Json(name = "episode")
    val episodeNumber: String,
    val characters: List<String>
)
