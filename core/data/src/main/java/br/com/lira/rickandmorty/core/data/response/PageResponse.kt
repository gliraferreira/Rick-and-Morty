package br.com.lira.rickandmorty.core.data.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PageResponse<T>(
    val info: PageInfoResponse,
    val results: List<T>
)
