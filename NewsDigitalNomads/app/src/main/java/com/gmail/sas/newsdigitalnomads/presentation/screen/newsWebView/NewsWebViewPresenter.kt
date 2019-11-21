package com.gmail.sas.newsdigitalnomads.presentation.screen.newsWebView

import com.arellomobile.mvp.InjectViewState
import com.gmail.sas.newsdigitalnomads.domain.entity.News
import com.gmail.sas.newsdigitalnomads.domain.usecase.GetListNewsFromDataSourceUseCase
import com.gmail.sas.newsdigitalnomads.presentation.common.BasePresenter
import javax.inject.Inject


@InjectViewState
class NewsWebViewPresenter @Inject constructor(
    private val getListNewsFromDataSourceUseCase: GetListNewsFromDataSourceUseCase
) :
    BasePresenter<NewsWebView>() {

    private var positionToPreviousPage = 0

    private var positionToNextPage = 0

    private var positionCurrentPage = 0

    private var listNews = listOf<News>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        listNews = getListNewsFromDataSourceUseCase.invoke()
    }

    fun onSetCurrentPositionPage(position: Int) {
        positionCurrentPage = position
        positionToNextPage = if (position + 1 < listNews.size) {
            position + 1
        } else {
            position
        }
        if (positionCurrentPage != 0) {
            positionToPreviousPage = position - 1
        }
    }

    fun onLoadNextPage() {
        listNews[positionToNextPage].linkToNews?.let { viewState?.loadingPage(it) }
        onSetCurrentPositionPage(positionToNextPage)
    }

    fun onLoadPreviousPage() {
        listNews[positionToPreviousPage].linkToNews?.let { viewState?.loadingPage(it) }
        onSetCurrentPositionPage(positionToPreviousPage)
    }

}