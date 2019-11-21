package com.gmail.sas.newsdigitalnomads.domain.usecase

import com.gmail.sas.newsdigitalnomads.domain.repository.DatabaseRepository
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class DeleteOldDataUseCaseImpl @Inject constructor(private val databaseRepository: DatabaseRepository) :
    DeleteOldDataUseCase {

    override fun invoke(): Completable = databaseRepository.deleteOldData(timeStampDate())
        .subscribeOn(Schedulers.io())


    private fun timeStampDate(): Long {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, -2)
        return calendar.time.time
    }
}