package com.github.ferum_bot.core_network.api

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor: Interceptor {

    companion object {

        const val API_KEY = "91b06a3b7bce462798be939236f73c2d"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url

        val newUrl = url.newBuilder()
            .addQueryParameter("key", API_KEY)
            .build()
        val newRequest = request.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}