package com.gmail.sas.newsdigitalnomads.dagger

import com.gmail.sas.newsdigitalnomads.App
import com.gmail.sas.newsdigitalnomads.dagger.data.DataModule
import com.gmail.sas.newsdigitalnomads.dagger.data.NightJobModule
import com.gmail.sas.newsdigitalnomads.dagger.data.database.RoomModule
import com.gmail.sas.newsdigitalnomads.dagger.data.network.ConverterModule
import com.gmail.sas.newsdigitalnomads.dagger.screen.ActivityModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        RoomModule::class,
        ConverterModule::class,
        ApiKeyModule::class,
        DataModule::class,
        NightJobModule::class]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<App>
}