package com.gmail.sas.newsdigitalnomads.presentation.screen.main

import com.arellomobile.mvp.InjectViewState
import com.gmail.sas.newsdigitalnomads.presentation.common.BasePresenter
import com.gmail.sas.newsdigitalnomads.worker.NightJobCreator
import javax.inject.Inject


@InjectViewState
class MainPresenter @Inject constructor(
    private val jobCreator: NightJobCreator
) : BasePresenter<MainView>() {


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun getInitializedJobCreator(): NightJobCreator = jobCreator

}