package br.com.lira.rickandmorty.core.toolkit

interface ResourceProvider {

    fun getString(resId: Int): String

    fun getString(resId: Int, vararg formatArgs: Any): String
}
