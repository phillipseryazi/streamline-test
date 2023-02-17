package com.example.streamlinetest.domain

import android.content.Context
import android.util.Log
import com.example.streamlinetest.data.remote.APIService
import com.example.streamlinetest.data.remote.NetworkConnectionInterceptor
import com.example.streamlinetest.data.remote.UniversityDTO
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteRepository(context: Context) {

    private val TAG = "RemoteRepository"

    private var retrofit: APIService

    private val BASE_URL = "http://universities.hipolabs.com/"

    init {
        val client = OkHttpClient.Builder()
            .addInterceptor(NetworkConnectionInterceptor(context))
            .build()

        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
            .create(APIService::class.java)
    }


    suspend fun getUniversities(): List<UniversityDTO>? {
        return try {
            val response = retrofit.getUniversities("Uganda")

            if (response.isSuccessful) {
                response.body()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            Log.e(TAG, "getUniversities: ${e.stackTrace}", e)
            emptyList()
        }
    }
}
