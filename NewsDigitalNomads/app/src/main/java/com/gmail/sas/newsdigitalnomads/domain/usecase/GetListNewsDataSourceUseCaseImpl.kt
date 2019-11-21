package com.gmail.sas.newsdigitalnomads.domain.usecase

import com.gmail.sas.newsdigitalnomads.domain.datasource.ListNewsDataSource
import com.gmail.sas.newsdigitalnomads.domain.entity.News
import javax.inject.Inject

class GetListNewsDataSourceUseCaseImpl @Inject constructor(private val listNewsDataSource: ListNewsDataSource) :
    GetListNewsFromDataSourceUseCase {

    override fun invoke(): List<News> = listNewsDataSource.getListNews()
}