package br.com.lira.rickandmorty.main.di

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin

val networkFlipper: NetworkFlipperPlugin = NetworkFlipperPlugin()

fun flipperInterceptor(): FlipperOkhttpInterceptor = FlipperOkhttpInterceptor(networkFlipper, true)
