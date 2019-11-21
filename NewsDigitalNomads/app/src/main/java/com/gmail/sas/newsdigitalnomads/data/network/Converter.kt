package com.gmail.sas.newsdigitalnomads.data.network

import com.gmail.sas.newsdigitalnomads.domain.entity.News

interface Converter<F, T> {

    fun convert(f: F): List<News>
}