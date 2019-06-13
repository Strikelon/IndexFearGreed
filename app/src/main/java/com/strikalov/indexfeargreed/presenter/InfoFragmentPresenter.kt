package com.strikalov.indexfeargreed.presenter

import android.util.Log
import com.arellomobile.mvp.MvpPresenter
import com.strikalov.indexfeargreed.view.InfoFragmentView

class InfoFragmentPresenter : MvpPresenter<InfoFragmentView>() {

    companion object {
        private const val TAG_LOG = "InfoFragmentPresenter"
    }

    override fun attachView(view: InfoFragmentView?) {
        super.attachView(view)
        Log.i(TAG_LOG, "attachView()")
    }
}