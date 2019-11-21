package com.gmail.sas.newsdigitalnomads.data.converter

import com.gmail.sas.newsdigitalnomads.data.database.entity.NewsEntity
import com.gmail.sas.newsdigitalnomads.data.network.model.NewsResponse
import java.util.*
import javax.inject.Inject

class ResponseToNewsEntityImpl @Inject constructor() :
    Converter<@JvmSuppressWildcards List<NewsResponse>, @JvmSuppressWildcards List<NewsEntity>> {

    override fun convertNewsList(listResponse: List<NewsResponse>): List<NewsEntity> =
        listResponse.map {
            NewsEntity(
                0,
                it.title, it.desc,
                it.urlImage, it.datePublished.time, it.linkToNews, Calendar.getInstance().time.time
            )
        }
}