package com.gmail.sas.newsdigitalnomads.worker

import com.evernote.android.job.DailyJob
import com.evernote.android.job.JobRequest
import com.gmail.sas.newsdigitalnomads.domain.usecase.DeleteOldDataUseCase
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class NightDailyJob @Inject constructor(private val dataUseCase: DeleteOldDataUseCase) :
    DailyJob() {


    companion object {

        const val TAG = "NIGHT_JOB"
        fun runScheduleNightJob() {
            val builder = JobRequest.Builder(TAG)
                .setRequiredNetworkType(JobRequest.NetworkType.UNMETERED)
            schedule(builder, TimeUnit.HOURS.toMillis(23), TimeUnit.HOURS.toMillis(4))
        }

    }

    override fun onRunDailyJob(params: Params): DailyJobResult {
        dataUseCase.invoke()
        return DailyJobResult.SUCCESS
    }
}