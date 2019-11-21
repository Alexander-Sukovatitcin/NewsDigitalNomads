package com.gmail.sas.newsdigitalnomads.domain.datasource

import com.gmail.sas.newsdigitalnomads.domain.entity.News

interface ListNewsDataSource {

    fun saveListNews(listNews: List<News>)

    fun getListNews(): List<News>

}