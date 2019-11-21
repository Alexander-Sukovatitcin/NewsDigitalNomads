package com.gmail.sas.newsdigitalnomads.domain.usecase

import com.gmail.sas.newsdigitalnomads.domain.entity.News
import io.reactivex.Completable


interface AddNewsDBUseCase {

    fun invoke(newsList: List<News>): Completable

}