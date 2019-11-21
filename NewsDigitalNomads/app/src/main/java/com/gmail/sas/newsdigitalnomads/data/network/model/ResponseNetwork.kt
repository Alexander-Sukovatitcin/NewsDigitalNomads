package com.gmail.sas.newsdigitalnomads.data.network.model

import com.google.gson.annotations.SerializedName


class ResponseNetwork(
    @SerializedName("articles") val response: List<NewsResponse>
)