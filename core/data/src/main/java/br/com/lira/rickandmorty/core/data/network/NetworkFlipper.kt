package br.com.lira.rickandmorty.core.data.network

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin

val networkFlipper: NetworkFlipperPlugin = NetworkFlipperPlugin()

fun flipperInterceptor(): FlipperOkhttpInterceptor = FlipperOkhttpInterceptor(networkFlipper, true)
