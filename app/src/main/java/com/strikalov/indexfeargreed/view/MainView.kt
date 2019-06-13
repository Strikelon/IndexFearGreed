package com.strikalov.indexfeargreed.view

import android.support.annotation.IdRes
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface MainView: MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun navigate(@IdRes destinationId: Int)

}