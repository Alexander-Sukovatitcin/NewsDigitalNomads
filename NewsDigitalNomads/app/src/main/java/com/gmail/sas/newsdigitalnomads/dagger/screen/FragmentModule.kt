package com.gmail.sas.newsdigitalnomads.dagger.screen

import com.gmail.sas.newsdigitalnomads.dagger.FragmentScope
import com.gmail.sas.newsdigitalnomads.presentation.screen.newsWebView.NewsWebFragment
import com.gmail.sas.newsdigitalnomads.presentation.screen.wallNews.WallNewsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
interface FragmentModule {

    @FragmentScope
    @ContributesAndroidInjector
    fun newsWallFragment(): WallNewsFragment

    @FragmentScope
    @ContributesAndroidInjector
    fun newsWebViewFragment(): NewsWebFragment
}