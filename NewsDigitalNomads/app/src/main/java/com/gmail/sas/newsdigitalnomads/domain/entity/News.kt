package com.gmail.sas.newsdigitalnomads.domain.entity

import java.util.*

data class News(
    val title: String?,
    val desc: String?,
    val urlImage: String?,
    val datePublished: Date,
    val linkToNews: String?
)