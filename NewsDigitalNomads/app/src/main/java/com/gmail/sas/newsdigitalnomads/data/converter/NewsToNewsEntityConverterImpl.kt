package com.gmail.sas.newsdigitalnomads.data.converter

import com.gmail.sas.newsdigitalnomads.data.database.entity.NewsEntity
import com.gmail.sas.newsdigitalnomads.domain.entity.News
import java.util.*
import javax.inject.Inject

class NewsToNewsEntityConverterImpl @Inject constructor() :
    Converter<@JvmSuppressWildcards List<News>, @JvmSuppressWildcards List<NewsEntity>> {

    override fun convertNewsList(listResponse: List<News>): List<NewsEntity> = listResponse.map {
        NewsEntity(
            0,
            it.title,
            it.desc,
            it.urlImage ?: "",
            convertDateToLong(it.datePublished),
            it.linkToNews,
            convertDateToLong(Calendar.getInstance().time)
        )
    }

    private fun convertDateToLong(date: Date?): Long = date?.time ?: 0L
}