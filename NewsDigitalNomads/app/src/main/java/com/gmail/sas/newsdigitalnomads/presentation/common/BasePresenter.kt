package com.gmail.sas.newsdigitalnomads.presentation.common

import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<V : BaseView> : MvpPresenter<V>() {

    protected val compositeDisposable = CompositeDisposable()

    override fun detachView(view: V) {
        super.detachView(view)
        compositeDisposable.clear()
    }
}