package com.sample.headlinesbyjusassignment.di.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.headlinesbyjusassignment.model.Article

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertArticles(movieList: List<Article>)

    @Query("SELECT * FROM Article")
    fun getArticleDetails(): List<Article>

    @Query("DELETE FROM Article")
    fun deleteArticleDetails()
}
