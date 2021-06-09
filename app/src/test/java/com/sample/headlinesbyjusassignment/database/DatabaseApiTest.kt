package com.sample.headlinesbyjusassignment.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sample.headlinesbyjusassignment.data.HeadlinesRepository
import com.sample.headlinesbyjusassignment.data.network.HeadlinesRemoteDataSource
import com.sample.headlinesbyjusassignment.di.database.AssignmentDatabase
import com.sample.headlinesbyjusassignment.di.network.RestInterface
import com.sample.headlinesbyjusassignment.model.Article
import com.sample.headlinesbyjusassignment.model.Result
import com.sample.headlinesbyjusassignment.util.TestUtil
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import retrofit2.Retrofit

@RunWith(JUnit4::class)

class DatabaseApiTest {

    private var headlinessDetail: List<Article>? = null

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var mockWebServer: MockWebServer
    private lateinit var headlinesRemoteDataSource: HeadlinesRemoteDataSource

    private val retrofit = Mockito.mock(Retrofit::class.java)
    private val service = Mockito.mock(RestInterface::class.java)
    private val database = Mockito.mock(AssignmentDatabase::class.java, Mockito.RETURNS_DEEP_STUBS)

    private lateinit var headlineRepository: HeadlinesRepository

    val result = TestUtil.loadJSONFromAsset()

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        headlinesRemoteDataSource = HeadlinesRemoteDataSource(retrofit, service)
        headlineRepository = HeadlinesRepository(headlinesRemoteDataSource, database.articleDao())
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @InternalCoroutinesApi
    @Test
    fun insertHeadlinesDetails() {
        runBlocking {
            flow {
                emit(Result.Status.LOADING)
                if (result?.status == "ok") {
                    result.articles?.let { it ->
                        database.articleDao().deleteArticleDetails()
                        database.articleDao().insertArticles(it)
                    }
                }
                emit(result)
            }
        }
    }

    @InternalCoroutinesApi
    @Test
    fun getHeadlinesDetail() {
        Mockito.`when`(database.articleDao().getArticleDetails()).thenReturn(result?.articles)
        val value = database.articleDao().getArticleDetails()
        assertThat(value?.get(0)?.id, `is`(result?.articles?.get(0)?.id))
    }
}
