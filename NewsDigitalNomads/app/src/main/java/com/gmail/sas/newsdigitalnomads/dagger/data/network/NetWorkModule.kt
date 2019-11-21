package com.gmail.sas.newsdigitalnomads.dagger.data.network

import com.gmail.sas.newsdigitalnomads.dagger.KeyApi
import com.gmail.sas.newsdigitalnomads.data.network.service.NewsService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module(
    includes = [
        ConverterModule::class
    ]
)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(@Named(KeyApi.SOURCE_URL) baseUrl: String): Retrofit {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val clientHttp = OkHttpClient.Builder().addInterceptor(logInterceptor).build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(clientHttp)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun newsService(retrofit: Retrofit): NewsService = retrofit.create(NewsService::class.java)

}