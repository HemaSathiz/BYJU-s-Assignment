package com.sample.headlinesbyjusassignment.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sample.headlinesbyjusassignment.model.Article
import com.sample.headlinesbyjusassignment.model.HeadlinesResponse
import java.io.IOException

object TestUtil {

    fun loadJSONFromAsset(): HeadlinesResponse? {
        var json: String? = null
        json = try {
            val inputStream = javaClass.classLoader!!.getResourceAsStream("api-response/sample.json")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return convertStringtoObject(json!!)
    }

    fun convertStringtoObject(json: String): HeadlinesResponse {
        return Gson().fromJson(
            json,
            HeadlinesResponse::class.java
        )
    }
}
