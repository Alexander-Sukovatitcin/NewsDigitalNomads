package com.gmail.sas.newsdigitalnomads.dagger.data

import com.gmail.sas.newsdigitalnomads.dagger.data.network.NetworkModule
import com.gmail.sas.newsdigitalnomads.data.datasource.ListNewsDataSourceImpl
import com.gmail.sas.newsdigitalnomads.data.repository.DatabaseRepositoryImpl
import com.gmail.sas.newsdigitalnomads.data.repository.NewsRepositoryImpl
import com.gmail.sas.newsdigitalnomads.domain.datasource.ListNewsDataSource
import com.gmail.sas.newsdigitalnomads.domain.repository.DatabaseRepository
import com.gmail.sas.newsdigitalnomads.domain.repository.NewsRepository
import com.gmail.sas.newsdigitalnomads.domain.usecase.*
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
interface DataModule {

    @Singleton
    @Binds
    fun newsListRepository(instance: NewsRepositoryImpl): NewsRepository

    @Singleton
    @Binds
    fun getNewsUseCase(instance: LoadNewsListUseCaseImpl): LoadNewsListUseCase

    @Singleton
    @Binds
    fun databaseRepository(instance: DatabaseRepositoryImpl): DatabaseRepository

    @Singleton
    @Binds
    fun addNewsDBUseCase(instance: AddNewsDBUseCaseImpl): AddNewsDBUseCase

    @Singleton
    @Binds
    fun listNewsDataSource(instance: ListNewsDataSourceImpl): ListNewsDataSource

    @Singleton
    @Binds
    fun saveListNewsUseCase(instance: SaveListNewsDataSourceUseCaseImpl): SaveListNewsDataSourceUseCase

    @Singleton
    @Binds
    fun getListNewsDataSource(instance: GetListNewsDataSourceUseCaseImpl): GetListNewsFromDataSourceUseCase

    @Singleton
    @Binds
    fun deleteOlDataUseCase(instance: DeleteOldDataUseCaseImpl): DeleteOldDataUseCase

}