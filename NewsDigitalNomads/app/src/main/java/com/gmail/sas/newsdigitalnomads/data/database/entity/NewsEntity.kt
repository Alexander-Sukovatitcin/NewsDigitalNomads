package com.gmail.sas.newsdigitalnomads.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "description") val desc: String?,
    @ColumnInfo(name = "url_image") val urlImage: String?,
    @ColumnInfo(name = "date_published") val datePublished: Long?,
    @ColumnInfo(name = "link_to_news") val linkToNews: String?,
    @ColumnInfo(name = "load_date") val loadDate: Long?
)