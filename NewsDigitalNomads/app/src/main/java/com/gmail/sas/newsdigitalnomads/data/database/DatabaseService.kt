package com.gmail.sas.newsdigitalnomads.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gmail.sas.newsdigitalnomads.data.database.dao.NewsDao
import com.gmail.sas.newsdigitalnomads.data.database.entity.NewsEntity


@Database(entities = arrayOf(NewsEntity::class), version = 1, exportSchema = false)
abstract class DatabaseService : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}