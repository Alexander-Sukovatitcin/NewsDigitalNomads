package com.gmail.sas.newsdigitalnomads.domain.repository

import com.gmail.sas.newsdigitalnomads.domain.entity.News
import io.reactivex.Single

interface NewsRepository {

    fun onLoadNews(page: Int): Single<List<News>>

}