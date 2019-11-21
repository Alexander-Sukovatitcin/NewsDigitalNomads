package com.gmail.sas.newsdigitalnomads.data.converter

interface Converter<R, T> {

    fun convertNewsList(r: R): T

}