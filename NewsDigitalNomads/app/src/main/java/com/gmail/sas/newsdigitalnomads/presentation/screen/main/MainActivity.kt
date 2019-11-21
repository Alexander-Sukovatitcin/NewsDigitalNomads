package com.gmail.sas.newsdigitalnomads.presentation.screen.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.evernote.android.job.JobManager
import com.gmail.sas.newsdigitalnomads.R
import com.gmail.sas.newsdigitalnomads.worker.NightDailyJob
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector, MainView {

    @Inject
    @InjectPresenter
    lateinit var mainPresenter: MainPresenter

    @ProvidePresenter
    fun provideMainPresenter(): MainPresenter = mainPresenter

    @Inject
    lateinit var supportFragmentInjectorImpl: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> =
        supportFragmentInjectorImpl


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        JobManager.create(applicationContext)
            .addJobCreator(mainPresenter.getInitializedJobCreator())
        NightDailyJob.runScheduleNightJob()
    }

}
