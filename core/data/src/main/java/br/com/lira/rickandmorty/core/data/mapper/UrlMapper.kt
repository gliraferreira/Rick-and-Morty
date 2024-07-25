package br.com.lira.rickandmorty.core.data.mapper

interface UrlMapper {

    fun mapId(url: String): String

    fun mapPage(url: String?): Int?
}