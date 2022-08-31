package br.com.lira.rickandmorty.core.mapper

import android.net.Uri
import javax.inject.Inject

class UrlDefaultMapper @Inject constructor() : UrlMapper {

    override fun mapId(url: String) = Uri.parse(url).lastPathSegment.orEmpty()
}