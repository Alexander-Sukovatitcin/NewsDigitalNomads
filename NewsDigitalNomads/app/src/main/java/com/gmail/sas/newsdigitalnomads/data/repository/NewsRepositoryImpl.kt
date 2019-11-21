package com.gmail.sas.newsdigitalnomads.data.repository

import android.util.Log
import com.gmail.sas.newsdigitalnomads.data.converter.Converter
import com.gmail.sas.newsdigitalnomads.data.network.model.NewsResponse
import com.gmail.sas.newsdigitalnomads.data.network.service.NewsService
import com.gmail.sas.newsdigitalnomads.domain.entity.News
import com.gmail.sas.newsdigitalnomads.domain.repository.NewsRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsService: NewsService,
    private val newsConverter: Converter<List<NewsResponse>, List<News>>
) : NewsRepository {

    companion object {
        private const val MAX_RETRY = 3L
    }

    override fun onLoadNews(page: Int): Single<List<News>> =
        newsService.getNews(page)
            .subscribeOn(Schedulers.io())
            .doOnError { Log.d("NewsRepositoryImpl", "Error load from news service") }
            .retryWhen {
                it.take(MAX_RETRY)
            }
            .map { item -> newsConverter.convertNewsList(item.response) }

}