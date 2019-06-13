package com.strikalov.indexfeargreed.presenter

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.strikalov.indexfeargreed.view.ChartFragmentView

@InjectViewState
class ChartFragmentPresenter : MvpPresenter<ChartFragmentView>(){

    companion object {
        private const val TAG_LOG = "ChartFragmentPresenter"
    }

    override fun attachView(view: ChartFragmentView?) {
        super.attachView(view)
        Log.i(TAG_LOG, "attachView()")
    }
}