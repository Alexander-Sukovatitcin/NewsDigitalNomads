package com.gmail.sas.newsdigitalnomads.worker

import com.evernote.android.job.Job
import com.evernote.android.job.JobCreator
import com.gmail.sas.newsdigitalnomads.domain.usecase.DeleteOldDataUseCase
import javax.inject.Inject

class NightJobCreator @Inject constructor(private val deleteOldDataUseCase: DeleteOldDataUseCase) : JobCreator {

    override fun create(tag: String): Job? = when (tag) {
        NightDailyJob.TAG -> {
            NightDailyJob(deleteOldDataUseCase)
        }
        else -> throw IllegalArgumentException()
    }
}