package br.com.lira.rickandmorty.features.shared.remote.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PageInfoResponse(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)
