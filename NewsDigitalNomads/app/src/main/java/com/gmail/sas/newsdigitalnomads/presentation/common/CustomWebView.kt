package com.gmail.sas.newsdigitalnomads.presentation.common

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Bitmap
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient


class CustomWebView(context: Context) : WebViewClient() {

    //Так как в тз прописано наличие блокирующего ProgressDialog, использую его не смотря на то что он deprecated
    val progressDialog = ProgressDialog.show(context, "Load page news", "Loading...")

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        view?.loadUrl(request?.url.toString())
        return true
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)


    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        progressDialog.dismiss()
    }
}