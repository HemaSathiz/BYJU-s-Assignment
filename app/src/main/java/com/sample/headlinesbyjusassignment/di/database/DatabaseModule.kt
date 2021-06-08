package com.sample.headlinesbyjusassignment.di.database

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideAssignmentDB(application: Application?): AssignmentDatabase {
        return Room.databaseBuilder(application!!, AssignmentDatabase::class.java, "Assignment")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideMoviesDao(articleDao: AssignmentDatabase): ArticleDao {
        return articleDao.articleDao()
    }
}
