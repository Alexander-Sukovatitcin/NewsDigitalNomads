package com.gmail.sas.newsdigitalnomads.domain.usecase

import com.gmail.sas.newsdigitalnomads.domain.entity.News
import com.gmail.sas.newsdigitalnomads.domain.repository.DatabaseRepository
import io.reactivex.Completable
import javax.inject.Inject

class AddNewsDBUseCaseImpl @Inject constructor(private val databaseRepository: DatabaseRepository) :
    AddNewsDBUseCase {

    override fun invoke(newsList: List<News>): Completable =
        databaseRepository.addNewListInDatabase(newsList)
}

