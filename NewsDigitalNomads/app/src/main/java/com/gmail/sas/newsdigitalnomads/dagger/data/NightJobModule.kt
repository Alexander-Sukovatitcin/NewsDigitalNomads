package com.gmail.sas.newsdigitalnomads.dagger.data

import com.gmail.sas.newsdigitalnomads.domain.usecase.DeleteOldDataUseCase
import com.gmail.sas.newsdigitalnomads.worker.NightJobCreator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NightJobModule {

    @Singleton
    @Provides
    fun provideJob(deleteOldDataUseCase: DeleteOldDataUseCase): NightJobCreator =
        NightJobCreator(deleteOldDataUseCase)
}