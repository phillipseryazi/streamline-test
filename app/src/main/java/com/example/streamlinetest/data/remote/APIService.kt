package com.example.streamlinetest.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("search")
    suspend fun getUniversities(@Query("country") country: String): Response<List<UniversityDTO>>
}
