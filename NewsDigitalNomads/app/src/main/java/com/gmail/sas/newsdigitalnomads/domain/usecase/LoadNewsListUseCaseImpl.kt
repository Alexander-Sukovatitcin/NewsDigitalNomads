package com.gmail.sas.newsdigitalnomads.domain.usecase

import com.gmail.sas.newsdigitalnomads.domain.entity.News
import com.gmail.sas.newsdigitalnomads.domain.repository.NewsRepository
import io.reactivex.Single
import javax.inject.Inject

class LoadNewsListUseCaseImpl @Inject constructor(private val newsRepository: NewsRepository) :
    LoadNewsListUseCase {

    override fun invoke(page: Int): Single<List<News>> =
        newsRepository.onLoadNews(page)

}