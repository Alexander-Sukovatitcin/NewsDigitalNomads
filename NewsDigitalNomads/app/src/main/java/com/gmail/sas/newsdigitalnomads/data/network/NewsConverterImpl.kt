package com.gmail.sas.newsdigitalnomads.data.network

import com.gmail.sas.newsdigitalnomads.data.network.model.NewsResponse
import com.gmail.sas.newsdigitalnomads.domain.entity.News
import javax.inject.Inject

class NewsConverterImpl @Inject constructor() :
    Converter<@JvmSuppressWildcards List<NewsResponse>, @JvmSuppressWildcards List<News>> {


    override fun convert(list: List<NewsResponse>): List<News> = list.map {
        News(
            it.title, it.desc,
            it.urlImage, it.datePublished, it.linkToNews
        )
    }
}