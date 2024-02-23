package com.example.newsapplication.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapplication.domain.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertArticle(article: Article)

    @Delete
   suspend fun deleteArticle(article: Article)

    @Query("Select * FROM Article")
    fun selectArticles(): Flow<List<Article>>

    @Query("Select * FROM Article where url =:url")
    fun selectArticle( url:String):Article?
}