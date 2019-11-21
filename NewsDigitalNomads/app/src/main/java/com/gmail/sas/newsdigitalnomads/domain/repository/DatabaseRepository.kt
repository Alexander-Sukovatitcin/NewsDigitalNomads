package com.gmail.sas.newsdigitalnomads.domain.repository

import com.gmail.sas.newsdigitalnomads.domain.entity.News
import io.reactivex.Completable

interface DatabaseRepository {

    fun addNewListInDatabase(newsList: List<News>): Completable

    fun deleteOldData(timestamp: Long): Completable
}