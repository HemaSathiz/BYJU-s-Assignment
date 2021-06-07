package com.sample.headlinesbyjusassignment.network

import com.sample.headlinesbyjusassignment.model.HeadlinesResponse
import com.sample.headlinesbyjusassignment.util.ConstantHelper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface RestInterface {
    @Headers("Content-Type: application/json")
    @GET("everything?q=tesla&from=2021-05-07&sortBy=publishedAt&apiKey=" + ConstantHelper.API_KEY)
    suspend fun getHeadlinesData(): Response<HeadlinesResponse>
}
