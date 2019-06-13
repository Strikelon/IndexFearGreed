package com.strikalov.indexfeargreed.presenter

import android.support.annotation.IdRes
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.strikalov.indexfeargreed.view.MainView


@InjectViewState
class MainPresenter : MvpPresenter<MainView>(){

    companion object {
        private const val TAG_LOG = "MainPresenterLog"
    }

    fun onNavigationItemSelected(@IdRes destinationId: Int){
        viewState.navigate(destinationId)
    }
}