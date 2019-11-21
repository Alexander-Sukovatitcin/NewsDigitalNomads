package com.gmail.sas.newsdigitalnomads.dagger

import com.gmail.sas.newsdigitalnomads.dagger.KeyApi.API_KEY
import com.gmail.sas.newsdigitalnomads.dagger.KeyApi.SOURCE_URL
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton


object KeyApi {

    const val API_KEY = "api_key"
    const val SOURCE_URL = "url"

}

@Module
class ApiKeyModule {

    @Singleton
    @Provides
    @Named(API_KEY)
    fun apiKey(): String = "26eddb253e7840f988aec61f2ece2907"


    @Singleton
    @Provides
    @Named(SOURCE_URL)
    fun sourceUrl(): String =
        "https://newsapi.org/v2/"

}