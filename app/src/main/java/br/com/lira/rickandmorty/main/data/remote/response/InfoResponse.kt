package br.com.lira.rickandmorty.main.data.remote.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class InfoResponse(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)
