package com.gmail.sas.newsdigitalnomads.presentation.common

import android.os.Bundle
import com.gmail.sas.newsdigitalnomads.presentation.moxy.MvpAppCompatFragment
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment : BaseView, MvpAppCompatFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

}