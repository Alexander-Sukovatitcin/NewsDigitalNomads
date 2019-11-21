package com.gmail.sas.newsdigitalnomads.data.network.model

import com.google.gson.annotations.SerializedName
import java.util.*

class NewsResponse(
    @SerializedName("title") val title: String,
    @SerializedName("description") val desc: String,
    @SerializedName("urlToImage") val urlImage: String,
    @SerializedName("publishedAt") val datePublished: Date,
    @SerializedName("url") val linkToNews: String
)