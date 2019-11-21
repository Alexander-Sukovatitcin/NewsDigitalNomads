package com.gmail.sas.newsdigitalnomads.dagger.data.network

import com.gmail.sas.newsdigitalnomads.data.converter.Converter
import com.gmail.sas.newsdigitalnomads.data.converter.NewsToNewsEntityConverterImpl
import com.gmail.sas.newsdigitalnomads.data.converter.ResponseToNewsConverterImpl
import com.gmail.sas.newsdigitalnomads.data.database.entity.NewsEntity
import com.gmail.sas.newsdigitalnomads.data.network.model.NewsResponse
import com.gmail.sas.newsdigitalnomads.domain.entity.News
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface ConverterModule {

    @Singleton
    @Binds
    fun convertNewsList(instance: ResponseToNewsConverterImpl): Converter<List<NewsResponse>, List<News>>

    @Singleton
    @Binds
    fun convertNewsEntity(instance: NewsToNewsEntityConverterImpl): Converter<List<News>, List<NewsEntity>>
}