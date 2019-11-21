package com.gmail.sas.newsdigitalnomads.data.datasource

import com.gmail.sas.newsdigitalnomads.domain.datasource.ListNewsDataSource
import com.gmail.sas.newsdigitalnomads.domain.entity.News
import javax.inject.Inject

class ListNewsDataSourceImpl @Inject constructor() : ListNewsDataSource {


    private var listNews = mutableListOf<News>()

    override fun saveListNews(listNews: List<News>) {
        this.listNews.addAll(listNews)
    }

    override fun getListNews(): List<News> = listNews
}