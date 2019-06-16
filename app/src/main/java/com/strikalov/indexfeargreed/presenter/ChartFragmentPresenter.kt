package com.strikalov.indexfeargreed.presenter

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.strikalov.indexfeargreed.model.interactors.FearGreedIndexInteractor
import com.strikalov.indexfeargreed.view.ChartFragmentView
import javax.inject.Inject
import javax.inject.Singleton

@InjectViewState
@Singleton
class ChartFragmentPresenter @Inject constructor(
    val fearGreedIndexInteractor: FearGreedIndexInteractor): MvpPresenter<ChartFragmentView>(){

    companion object {
        private const val TAG_LOG = "ChartFragmentPresenter"
    }

    override fun attachView(view: ChartFragmentView?) {
        super.attachView(view)
        Log.i(TAG_LOG, "attachView()")
    }
}