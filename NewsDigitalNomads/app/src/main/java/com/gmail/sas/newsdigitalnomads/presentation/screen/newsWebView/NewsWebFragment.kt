package com.gmail.sas.newsdigitalnomads.presentation.screen.newsWebView

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.graphics.Bitmap
import android.os.Bundle
import android.view.*
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.GestureDetectorCompat
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.gmail.sas.newsdigitalnomads.R
import com.gmail.sas.newsdigitalnomads.presentation.common.BaseFragment
import kotlinx.android.synthetic.main.news_web_view.*
import javax.inject.Inject
import kotlin.math.abs

class NewsWebFragment : BaseFragment(), NewsWebView, GestureDetector.OnGestureListener {

    @Inject
    @InjectPresenter
    lateinit var newsWebPresenter: NewsWebViewPresenter

    @ProvidePresenter
    fun provideNewsWebPresenter(): NewsWebViewPresenter = newsWebPresenter

    //Так как в тз прописано наличие блокирующего ProgressDialog, использую его не смотря на то что он deprecated
    private lateinit var progressDialog: ProgressDialog


    private lateinit var gesDetector: GestureDetectorCompat

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.news_web_view, container, false)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressDialog = ProgressDialog(context)
        progressDialog.setTitle(getString(R.string.progress_dialog_title))
        progressDialog.setMessage(getString(R.string.progress_dialog_msg))
        webViewCustomClientInit()
        val url = arguments?.let { NewsWebFragmentArgs.fromBundle(it).newsUrl }
        val position = arguments?.let { NewsWebFragmentArgs.fromBundle(it).positionNews }
        position?.let { newsWebPresenter.onSetCurrentPositionPage(it) }
        url?.let { loadingPage(it) }
    }

    override fun loadingPage(linkNextPage: String) {
        webViewNews.loadUrl(linkNextPage)
    }



    override fun onShowPress(p0: MotionEvent?) {

    }

    override fun onSingleTapUp(p0: MotionEvent?): Boolean {
        return false
    }

    override fun onDown(p0: MotionEvent?): Boolean {
        return false
    }

    override fun onFling(
        p0: MotionEvent?,
        p1: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        var result = false
        val diffY = p0?.y?.let { p1?.y?.minus(it) } ?: 0F
        val diffX = p0?.x?.let { p1?.x?.minus(it) } ?: 0F
        val velX = velocityX ?: 0F
        if (abs(diffX) > abs(diffY)) {
            if (abs(diffX) > 100 && abs(velX) > 100) {
                result = if (diffX > 0) {
                    newsWebPresenter.onLoadPreviousPage()
                    true
                } else {
                    newsWebPresenter.onLoadNextPage()
                    true
                }
            }
        }

        return result
    }

    override fun onScroll(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        return false
    }

    override fun onLongPress(p0: MotionEvent?) {

    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun webViewCustomClientInit() {
        gesDetector = GestureDetectorCompat(context, this)
        webViewNews.setOnTouchListener { view, motionEvent -> gesDetector.onTouchEvent(motionEvent) }
        webViewNews.settings.javaScriptEnabled = true

        webViewNews.webViewClient = object : WebViewClient() {


            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progressDialog.show()
            }

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                view?.loadUrl(request?.url.toString())
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if (progressDialog.isShowing) {
                    progressDialog.dismiss()
                }
            }

            override fun onReceivedError(
                view: WebView?,
                errorCode: Int,
                description: String?,
                failingUrl: String?
            ) {
                super.onReceivedError(view, errorCode, description, failingUrl)
            }
        }

    }


}