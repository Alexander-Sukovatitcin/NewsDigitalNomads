package com.gmail.sas.newsdigitalnomads.domain.usecase

import com.gmail.sas.newsdigitalnomads.domain.datasource.ListNewsDataSource
import com.gmail.sas.newsdigitalnomads.domain.entity.News
import javax.inject.Inject

class SaveListNewsDataSourceUseCaseImpl @Inject constructor(private val listNewsDataSource: ListNewsDataSource) :
    SaveListNewsDataSourceUseCase {

    override fun invoke(listNews: List<News>) {
        listNewsDataSource.saveListNews(listNews)
    }
}