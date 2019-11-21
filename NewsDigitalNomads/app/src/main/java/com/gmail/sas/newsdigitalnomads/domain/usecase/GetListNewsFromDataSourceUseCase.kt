package com.gmail.sas.newsdigitalnomads.domain.usecase

import com.gmail.sas.newsdigitalnomads.domain.entity.News

interface GetListNewsFromDataSourceUseCase {

    fun invoke(): List<News>

}