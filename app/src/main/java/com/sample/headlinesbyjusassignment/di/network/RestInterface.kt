package com.sample.headlinesbyjusassignment.di.network

import com.sample.headlinesbyjusassignment.model.HeadlinesResponse
import com.sample.headlinesbyjusassignment.util.ConstantHelper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RestInterface {
    @Headers("Content-Type: application/json")
    @GET("everything?q=tesla")
    suspend fun getHeadlinesData( @Query("from") date: String, @Query("sortBy") sortBy: String, @Query("apiKey") apiKey: String): Response<HeadlinesResponse>
}
