package com.sample.headlinesbyjusassignment.data

import com.sample.headlinesbyjusassignment.data.network.HeadlinesRemoteDataSource
import com.sample.headlinesbyjusassignment.di.database.ArticleDao
import com.sample.headlinesbyjusassignment.model.HeadlinesResponse
import com.sample.headlinesbyjusassignment.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HeadlinesRepository @Inject constructor(
    private val movieRemoteDataSource: HeadlinesRemoteDataSource,
    private val articleDao: ArticleDao
) {

    suspend fun fetchHeadlines(): Flow<Result<HeadlinesResponse>?> {
        return flow {
            emit(fetchHeadlinesCatched())
            emit(Result.loading())
            val result = movieRemoteDataSource.fetchHeadlinesData()

            if (result.status == Result.Status.SUCCESS) {
                result.data?.articles?.let { it ->
                    articleDao.deleteArticleDetails()
                    articleDao.insertArticles(it)
                    emit(result)
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    private fun fetchHeadlinesCatched(): Result<HeadlinesResponse>? =
        articleDao.getArticleDetails()?.let {
            Result.success(HeadlinesResponse(it, "Success", 0))
        }
}
