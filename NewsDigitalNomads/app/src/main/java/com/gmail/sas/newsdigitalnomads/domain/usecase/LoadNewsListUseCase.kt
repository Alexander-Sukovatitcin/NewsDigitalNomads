package com.gmail.sas.newsdigitalnomads.domain.usecase

import com.gmail.sas.newsdigitalnomads.domain.entity.News
import io.reactivex.Single

interface LoadNewsListUseCase {

    operator fun invoke(page: Int): Single<List<News>>
}