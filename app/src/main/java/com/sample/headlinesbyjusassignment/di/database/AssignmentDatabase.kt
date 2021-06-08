package com.sample.headlinesbyjusassignment.di.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sample.headlinesbyjusassignment.model.Article

@Database(
    entities = [Article::class],
    version = 1, exportSchema = true
)
@TypeConverters(SourceTypeConverter::class)

abstract class AssignmentDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}
