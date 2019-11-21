package com.gmail.sas.newsdigitalnomads.domain.usecase

import com.gmail.sas.newsdigitalnomads.domain.entity.News

interface SaveListNewsDataSourceUseCase {

    fun invoke(listNews: List<News>)
}