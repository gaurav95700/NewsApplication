package com.example.newsapp.data.repository

import com.example.newsapp.data.api.NetworkService
import com.example.newsapp.data.local.NewsDatabase
import com.example.newsapp.data.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopHeadlineRepository @Inject constructor(
    private val networkService: NetworkService,
    private val newsDatabase: NewsDatabase,
) {

    fun getTopHeadlines(country: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getTopHeadlines(country))
        }.map {
            it.articles
        }
    }


    fun getTopHeadlinesFromLocalDb(): Flow<List<Article>> {
        return flow {
            emit(newsDatabase.articleDao().getAllArticles())
        }
    }

    fun insertArticlesToLocalDb(articles: List<Article>): Flow<Unit> {
        return flow {
            emit(newsDatabase.articleDao().insertArticles(articles))
        }
    }

    fun deleteArticlesFromLocalDb(): Flow<Unit> {
        return flow {
            emit(newsDatabase.articleDao().deleteAllArticles())
        }
    }

}


