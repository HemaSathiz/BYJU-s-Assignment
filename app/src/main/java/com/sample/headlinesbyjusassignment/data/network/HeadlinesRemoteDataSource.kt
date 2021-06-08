package com.sample.headlinesbyjusassignment.data.network

import com.sample.headlinesbyjusassignment.model.HeadlinesResponse
import com.sample.headlinesbyjusassignment.model.Result
import com.sample.headlinesbyjusassignment.di.network.RestInterface
import com.sample.headlinesbyjusassignment.util.ErrorUtils
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class HeadlinesRemoteDataSource @Inject constructor(private val retrofit: Retrofit, private val apiService: RestInterface) {

    suspend fun fetchHeadlinesData(): Result<HeadlinesResponse> {

        return getResponse(
            request = { apiService.getHeadlinesData() },
            defaultErrorMessage = "Error fetching Headlines List"
        )
    }

    private suspend fun <T> getResponse(request: suspend () -> Response<T>, defaultErrorMessage: String): Result<T> {
        return try {
            println("I'm working in thread ${Thread.currentThread().name}")
            val result = request.invoke()
            if (result.isSuccessful) {
                return Result.success(result.body())
            } else {
                val errorResponse = ErrorUtils.parseError(result, retrofit)
                Result.error(errorResponse?.status_message ?: defaultErrorMessage, errorResponse)
            }
        } catch (e: Throwable) {
            Result.error("Unknown Error", null)
        }
    }
}
