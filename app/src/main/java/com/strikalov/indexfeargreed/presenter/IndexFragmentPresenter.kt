package com.strikalov.indexfeargreed.presenter

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.strikalov.indexfeargreed.view.IndexFragmentView

@InjectViewState
class IndexFragmentPresenter : MvpPresenter<IndexFragmentView>(){

    companion object {
        private const val TAG_LOG = "IndexFragmentPresenter"
    }

    override fun attachView(view: IndexFragmentView?) {
        super.attachView(view)
        Log.i(TAG_LOG, "attachView()")
    }

}