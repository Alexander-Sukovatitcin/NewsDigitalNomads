package com.gmail.sas.newsdigitalnomads.data.database

import androidx.room.TypeConverter
import java.util.*

class DateConverter {

    @TypeConverter
    fun dateToTimestamp(date: Date): Long = date.time


}