package com.valter.marvelcomics.data.network

import com.valter.marvelcomics.BuildConfig
import com.valter.marvelcomics.utils.md5Hash
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptorImpl : RequestInterceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val timestamp = System.currentTimeMillis() / 1000
        val apiKey = BuildConfig.API_KEY
        val privateKey = BuildConfig.PRIVATE_KEY
        val hashedStr = md5Hash("$timestamp$privateKey$apiKey")

        val url = chain.request()
                .url
                .newBuilder()
                .addQueryParameter("ts", timestamp.toString())
                .addQueryParameter("apikey", BuildConfig.API_KEY)
                .addQueryParameter("hash", hashedStr)
                .build()

        val request = chain.request()
                .newBuilder()
                .url(url)
                .build()

        return chain.proceed(request)
    }
}