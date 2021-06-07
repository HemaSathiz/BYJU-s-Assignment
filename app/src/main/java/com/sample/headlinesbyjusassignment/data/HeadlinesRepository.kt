package com.sample.headlinesbyjusassignment.data

import com.sample.headlinesbyjusassignment.data.network.HeadlinesRemoteDataSource
import com.sample.headlinesbyjusassignment.model.HeadlinesResponse
import com.sample.headlinesbyjusassignment.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HeadlinesRepository @Inject constructor(
    private val movieRemoteDataSource: HeadlinesRemoteDataSource
) {

    suspend fun fetchHeadlines(): Flow<Result<HeadlinesResponse>?> {
        return flow {
            emit(Result.loading())
            val result = movieRemoteDataSource.fetchRecepies()
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}
