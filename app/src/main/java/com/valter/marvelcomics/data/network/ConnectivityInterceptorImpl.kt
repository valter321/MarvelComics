package com.valter.marvelcomics.data.network

import android.content.Context
import com.valter.marvelcomics.data.network.ConnectivityInterceptor
import com.valter.marvelcomics.utils.NoConnectivityException
import com.valter.marvelcomics.utils.isConnectedToNetwork
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterceptorImpl(
        context: Context
) : ConnectivityInterceptor {

    private val appContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if(!appContext.isConnectedToNetwork())
            throw NoConnectivityException()
        return chain.proceed(chain.request())
    }
}