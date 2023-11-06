package br.com.lira.rickandmorty.core.toolkit

inline fun <reified T : Enum<T>> valueOf(type: String, default: T): T {
    return try {
        java.lang.Enum.valueOf(T::class.java, type)
    } catch (e: IllegalArgumentException) {
        return default
    }
}