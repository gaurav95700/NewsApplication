package com.example.newsapp.ui.topheadline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.repository.TopHeadlineRepository
import com.example.newsapp.ui.base.UiState
import com.example.newsapp.utils.AppConstant.COUNTRY
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class TopHeadlineViewModel(private val topHeadlineRepository: TopHeadlineRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            topHeadlineRepository.getTopHeadlines(COUNTRY)
                .catch { e ->
                    getAllNews()
                }.collect {
                    deleteNews(it)
                }
        }
    }


    private fun deleteNews(articles: List<Article>) {
        viewModelScope.launch {
            topHeadlineRepository.deleteArticlesFromLocalDb()
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    insertNews(articles)
                }
        }
    }


    private fun insertNews(articles: List<Article>) {
        viewModelScope.launch {
            topHeadlineRepository.insertArticlesToLocalDb(articles)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    getAllNews()
                }
        }
    }


    private fun getAllNews() {
        viewModelScope.launch {
            topHeadlineRepository.getTopHeadlinesFromLocalDb()
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }


}