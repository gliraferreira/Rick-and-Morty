package br.com.lira.rickandmorty.core.data.mapper

import android.net.Uri
import javax.inject.Inject


class UrlDefaultMapper @Inject constructor() : UrlMapper {

    override fun mapId(url: String) = Uri.parse(url).lastPathSegment.orEmpty()

    override fun mapPage(url: String?) = url?.let(Uri::parse)?.getQueryParameter(PAGE_PARAM)?.toInt()

    companion object {
        private const val PAGE_PARAM = "page"
    }
}