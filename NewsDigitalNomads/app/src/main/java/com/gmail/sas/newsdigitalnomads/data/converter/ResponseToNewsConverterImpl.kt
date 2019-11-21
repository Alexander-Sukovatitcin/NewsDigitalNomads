package com.gmail.sas.newsdigitalnomads.data.converter

import com.gmail.sas.newsdigitalnomads.data.network.model.NewsResponse
import com.gmail.sas.newsdigitalnomads.domain.entity.News
import javax.inject.Inject

class ResponseToNewsConverterImpl @Inject constructor() :
    Converter<@JvmSuppressWildcards List<NewsResponse>, @JvmSuppressWildcards List<News>> {


    override fun convertNewsList(listResponse: List<NewsResponse>): List<News> =
        listResponse.map {
            News(
                it.title, it.desc,
                it.urlImage, it.datePublished, it.linkToNews
            )
        }
}