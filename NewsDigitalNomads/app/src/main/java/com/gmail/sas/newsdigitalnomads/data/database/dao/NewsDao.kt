package com.gmail.sas.newsdigitalnomads.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gmail.sas.newsdigitalnomads.data.database.entity.NewsEntity
import io.reactivex.Completable

@Dao
interface NewsDao {

    @Query("SELECT * FROM NewsEntity")
    fun getAllNews(): List<NewsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllNews(listNews: List<NewsEntity>): Completable

    @Query(
        "DELETE \n" +
                "FROM NewsEntity\n" +
                "WHERE load_date < :timestamp"
    )
    fun deleteOldData(timestamp: Long): Completable
}