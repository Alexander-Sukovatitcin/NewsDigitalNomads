package com.gmail.sas.newsdigitalnomads.dagger.data.database

import androidx.room.Room
import com.gmail.sas.newsdigitalnomads.App
import com.gmail.sas.newsdigitalnomads.data.database.DatabaseService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideAppDatabase(app: App): DatabaseService =
        Room.databaseBuilder(app.applicationContext, DatabaseService::class.java, "news-database")
            .build()


}