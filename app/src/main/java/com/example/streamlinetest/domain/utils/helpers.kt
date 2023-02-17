package com.example.streamlinetest.domain.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import java.io.IOException

class NoNetworkException(message: String) : IOException(message)

@SuppressLint("MissingPermission")
fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager =
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    var result = false
    connectivityManager.let {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            it.getNetworkCapabilities(connectivityManager.activeNetwork)
                ?.apply {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> true
                        else -> false
                    }
                }
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            result = networkInfo != null && networkInfo.isAvailable
        }
    }
    return result
}
