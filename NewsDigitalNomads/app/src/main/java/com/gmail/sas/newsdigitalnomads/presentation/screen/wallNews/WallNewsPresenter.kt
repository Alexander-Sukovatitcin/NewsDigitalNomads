package com.gmail.sas.newsdigitalnomads.presentation.screen.wallNews

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.gmail.sas.newsdigitalnomads.domain.entity.News
import com.gmail.sas.newsdigitalnomads.domain.usecase.AddNewsDBUseCase
import com.gmail.sas.newsdigitalnomads.domain.usecase.LoadNewsListUseCase
import com.gmail.sas.newsdigitalnomads.domain.usecase.SaveListNewsDataSourceUseCase
import com.gmail.sas.newsdigitalnomads.presentation.common.BasePresenter
import com.gmail.sas.newsdigitalnomads.presentation.common.Paginator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class WallNewsPresenter @Inject constructor(
    private val loadNewsListUseCase: LoadNewsListUseCase,
    private val addNewsDBUseCase: AddNewsDBUseCase,
    private val saveListNewsDataSourceUseCase: SaveListNewsDataSourceUseCase
) : BasePresenter<WallNewsView>() {

    private val paginator = Paginator({ item ->
        loadNewsListUseCase.invoke(item)
            .doOnError {
                Log.d("NewsRepositoryImpl", "error")
                viewState?.hideLoadingBar()
                if (item == 1) {
                    viewState?.showRefreshButton()
                } else {
                    viewState?.showRepeatButton()
                }
            }
            .doOnSuccess { Log.d("NewsRepositoryImpl", "success") }
            .observeOn(AndroidSchedulers.mainThread())


    }, object : Paginator.ViewController<News> {
        override fun showEmptyProgress(show: Boolean) {


        }

        override fun showEmptyError(show: Boolean, error: Throwable?) {

        }

        override fun showEmptyView(show: Boolean) {

        }

        override fun showData(show: Boolean, data: List<News>) {
            addNewsDBUseCase.invoke(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete {
                    viewState?.hideLoadingBar()
                    viewState?.setListAdapter(data)
                    saveListNewsDataSourceUseCase.invoke(data)
                }
                .doOnError {
                    viewState?.showRepeatButton()

                }
                .subscribe()
        }

        override fun showErrorMessage(error: Throwable) {
            viewState?.showRepeatButton()
        }

        override fun showRefreshProgress(show: Boolean) {

        }

        override fun showPageProgress(show: Boolean) {
            viewState?.showPageProgress()
        }

    })


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState?.showLoadingBar()
        paginator.refresh()

    }


    override fun onDestroy() {
        super.onDestroy()
        paginator.release()
    }

    fun onRefreshWall() {
        paginator.refresh()
    }

    fun onRefreshEmptyWall() {
        viewState.showLoadingBar()
        paginator.refresh()

    }

    fun onLoadNews() {
        paginator.loadNewPage()
    }
}