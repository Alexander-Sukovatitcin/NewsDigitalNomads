package com.gmail.sas.newsdigitalnomads.presentation.screen.wallNews

import android.animation.Animator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.gmail.sas.newsdigitalnomads.R
import com.gmail.sas.newsdigitalnomads.domain.entity.News
import com.gmail.sas.newsdigitalnomads.presentation.common.BaseFragment
import com.gmail.sas.newsdigitalnomads.presentation.model.WallNewsItem
import kotlinx.android.synthetic.main.wall_news_fragment.*
import javax.inject.Inject


class WallNewsFragment : BaseFragment(), WallNewsView {

    @Inject
    @InjectPresenter
    lateinit var wallNewsPresenter: WallNewsPresenter

    @ProvidePresenter
    fun provideWallNewsPresenter(): WallNewsPresenter = wallNewsPresenter

    lateinit var actionWallToWeb: WallNewsFragmentDirections.ActionWallToWeb
    private val wallAdapter = WallAdapter { wallNewsPresenter.onLoadNews() }

    private val animatorListenerRefreshBtn = object : Animator.AnimatorListener {
        override fun onAnimationRepeat(p0: Animator?) {
        }

        override fun onAnimationEnd(p0: Animator?) {
            refreshButton.animate().rotation(0F).setListener(null).start()
        }

        override fun onAnimationCancel(p0: Animator?) {

        }

        override fun onAnimationStart(p0: Animator?) {

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.wall_news_fragment, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actionWallToWeb = WallNewsFragmentDirections.actionWallToWeb()
        initWall()
        initListener()
        refreshButton.animate().alpha(0f).setDuration(100).start()
        wallNewsPresenter.onLoadNews()

    }


    override fun showLoadingBar() {
        wallProgressBar.visibility = View.VISIBLE
    }

    override fun hideLoadingBar() {
        wallProgressBar.visibility = View.INVISIBLE
    }

    override fun setListAdapter(item: List<News>) {
        refreshButton.visibility = View.INVISIBLE
        wallAdapter.setItems(item.map {
            WallNewsItem(
                it.title,
                it.desc,
                it.urlImage,
                it.datePublished,
                it.linkToNews
            )
        })
    }

    override fun showRepeatButton() {
        wallAdapter.showRepeatButton()
    }

    override fun showPageProgress() {
        wallAdapter.showPageProgressBar()
    }

    override fun showRefreshButton() {
        refreshButton.isEnabled = true
        refreshButton.visibility = View.VISIBLE
        refreshButton.animate().alpha(1F).start()

    }

    private fun initListener() {
        wallAdapter.addOnClickNews {
            actionWallToWeb.newsUrl = wallAdapter.getLinkToNews(it.adapterPosition).toString()
            actionWallToWeb.positionNews = it.adapterPosition
            wallAdapter.clearList()
            view?.findNavController()?.navigate(actionWallToWeb)
        }
        wallAdapter.addOnClickRepeat { wallNewsPresenter.onRefreshWall() }
        refreshButton.setOnClickListener {
            refreshButton.animate().rotation(360F).setDuration(400)
                .setListener(animatorListenerRefreshBtn).start()
            refreshButton.animate().alpha(0F).setStartDelay(100).setDuration(500).start()
            refreshButton.isEnabled = false
            wallNewsPresenter.onRefreshEmptyWall()
        }
    }

    private fun initWall() {
        wallRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = wallAdapter
        }
    }

}