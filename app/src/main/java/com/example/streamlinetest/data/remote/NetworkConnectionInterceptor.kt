package com.example.streamlinetest.data.remote

import android.content.Context
import com.example.streamlinetest.domain.utils.NoNetworkException
import com.example.streamlinetest.domain.utils.isNetworkAvailable
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(private val context: Context) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!context.isNetworkAvailable()) {
            throw NoNetworkException("You have no network access")
        }
        return chain.proceed(chain.request())
    }
}
