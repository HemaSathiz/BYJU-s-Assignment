package com.sample.headlinesbyjusassignment.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sample.headlinesbyjusassignment.data.HeadlinesRepository
import com.sample.headlinesbyjusassignment.data.network.HeadlinesRemoteDataSource
import com.sample.headlinesbyjusassignment.di.database.AssignmentDatabase
import com.sample.headlinesbyjusassignment.di.network.RestInterface
import com.sample.headlinesbyjusassignment.model.Article
import com.sample.headlinesbyjusassignment.util.TestUtil
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import retrofit2.Retrofit

@RunWith(JUnit4::class)

class NetworkApiTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var mockWebServer: MockWebServer
    private lateinit var headlinesRemoteDataSource: HeadlinesRemoteDataSource

    private val retrofit = Mockito.mock(Retrofit::class.java)
    private val service = Mockito.mock(RestInterface::class.java)
    private val database = Mockito.mock(AssignmentDatabase::class.java, Mockito.RETURNS_DEEP_STUBS)
    val result = TestUtil.loadJSONFromAsset()

    private lateinit var headlineRepository: HeadlinesRepository

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

    @Test
    fun getRepos() {
        enqueueResponse("sample.json")
        runBlocking {
            launch {

                headlineRepository.fetchHeadlines().collect {
                    Mockito.`when`(it?.data).thenReturn(result)
                    assertThat(result?.articles?.size, CoreMatchers.`is`("20"))
                }
            }
        }
    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream("api-response/$fileName")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(
            mockResponse
                .setBody(source.readString(Charsets.UTF_8))
        )
    }
}
