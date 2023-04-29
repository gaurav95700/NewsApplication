package com.example.newsapp.data.local.dao

import androidx.room.*
import com.example.newsapp.data.model.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(articles: List<Article>)

    @Query("select * from article")
    fun getAllArticles(): List<Article>

    @Query("delete from article")
    fun deleteAllArticles()

}
