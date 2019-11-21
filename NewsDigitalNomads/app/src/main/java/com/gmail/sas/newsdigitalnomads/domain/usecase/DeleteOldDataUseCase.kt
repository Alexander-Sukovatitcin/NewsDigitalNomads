package com.gmail.sas.newsdigitalnomads.domain.usecase

import io.reactivex.Completable

interface DeleteOldDataUseCase {

    fun invoke(): Completable

}