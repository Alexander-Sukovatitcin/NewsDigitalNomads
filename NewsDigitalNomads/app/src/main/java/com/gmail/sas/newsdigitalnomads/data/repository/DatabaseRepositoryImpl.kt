package com.gmail.sas.newsdigitalnomads.data.repository

import com.gmail.sas.newsdigitalnomads.data.converter.Converter
import com.gmail.sas.newsdigitalnomads.data.database.DatabaseService
import com.gmail.sas.newsdigitalnomads.data.database.entity.NewsEntity
import com.gmail.sas.newsdigitalnomads.domain.entity.News
import com.gmail.sas.newsdigitalnomads.domain.repository.DatabaseRepository
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(
    private val databaseService: DatabaseService,
    private val responseConverter: Converter<List<News>, List<NewsEntity>>
) : DatabaseRepository {

    override fun addNewListInDatabase(newsList: List<News>): Completable =
        databaseService.newsDao().insertAllNews(responseConverter.convertNewsList(newsList))

    override fun deleteOldData(timestamp: Long): Completable =
        databaseService.newsDao().deleteOldData(timestamp)
            .subscribeOn(Schedulers.io())

}