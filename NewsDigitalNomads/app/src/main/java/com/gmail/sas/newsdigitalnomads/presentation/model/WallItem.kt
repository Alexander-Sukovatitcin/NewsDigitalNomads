package com.gmail.sas.newsdigitalnomads.presentation.model

import java.util.*

interface WallItem

class WallNewsItem(
    val title: String?,
    val desc: String?,
    val urlImage: String?,
    val datePublished: Date,
    val linkToNews: String?
) : WallItem

class WallRepeatButton() : WallItem