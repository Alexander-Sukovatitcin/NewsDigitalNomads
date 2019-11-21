package com.gmail.sas.newsdigitalnomads.dagger.screen

import com.gmail.sas.newsdigitalnomads.dagger.ActivityScope
import com.gmail.sas.newsdigitalnomads.presentation.screen.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    fun provideMainActivity(): MainActivity
}