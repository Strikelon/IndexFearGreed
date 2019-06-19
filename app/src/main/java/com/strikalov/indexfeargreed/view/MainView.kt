package com.strikalov.indexfeargreed.view

import android.support.annotation.IdRes
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface MainView: MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun navigate(@IdRes destinationId: Int)

}