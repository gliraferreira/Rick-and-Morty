package br.com.lira.rickandmorty.features.shared.remote.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PageResponse<T>(
    val info: PageInfoResponse,
    val results: List<T>
)
