package com.strikalov.indexfeargreed.presenter

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.strikalov.indexfeargreed.view.InfoFragmentView
import javax.inject.Inject
import javax.inject.Singleton

@InjectViewState
@Singleton
class InfoFragmentPresenter @Inject constructor(): MvpPresenter<InfoFragmentView>() {

    companion object {
        private const val TAG_LOG = "InfoFragmentPresenter"
    }

    override fun attachView(view: InfoFragmentView?) {
        super.attachView(view)
        Log.i(TAG_LOG, "attachView()")
    }
}