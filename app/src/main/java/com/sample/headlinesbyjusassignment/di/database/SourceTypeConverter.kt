package com.sample.headlinesbyjusassignment.di.database

import androidx.room.TypeConverter
import com.sample.headlinesbyjusassignment.model.Source
import org.json.JSONObject

class SourceTypeConverter {
    @TypeConverter
    fun fromSource(source: Source): String {
        return JSONObject().apply {
            put("name", source.name)
        }.toString()
    }

    @TypeConverter
    fun toSource(source: String): Source {
        val json = JSONObject(source)
        return Source(json.getString("name"))
    }
}
