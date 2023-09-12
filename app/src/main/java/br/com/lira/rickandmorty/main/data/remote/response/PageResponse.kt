package br.com.lira.rickandmorty.main.data.remote.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PageResponse<T>(
    val info: PageInfoResponse,
    val results: List<T>
)
