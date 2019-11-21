package com.gmail.sas.newsdigitalnomads.presentation.screen.wallNews

import com.gmail.sas.newsdigitalnomads.domain.entity.News
import com.gmail.sas.newsdigitalnomads.presentation.common.BaseView

interface WallNewsView : BaseView {

    fun setListAdapter(item: List<News>)

    fun showRepeatButton()

    fun showLoadingBar()

    fun hideLoadingBar()

    fun showRefreshButton()

    fun showPageProgress()

}