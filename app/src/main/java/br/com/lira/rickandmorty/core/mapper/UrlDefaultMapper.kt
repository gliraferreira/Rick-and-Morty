package br.com.lira.rickandmorty.core.mapper

import android.net.Uri
import javax.inject.Inject

private const val PAGE_PARAM = "page"

class UrlDefaultMapper @Inject constructor() : UrlMapper {

    override fun mapId(url: String) = Uri.parse(url).lastPathSegment.orEmpty()

    override fun mapPage(url: String?) = url?.let(Uri::parse)?.getQueryParameter(PAGE_PARAM)?.toInt()
}