package com.strikalov.indexfeargreed.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface IndexFragmentView : MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun swipeRefreshShow()

    @StateStrategyType(SkipStrategy::class)
    fun swipeRefreshHide()

    @StateStrategyType(SkipStrategy::class)
    fun isOnline()

    @StateStrategyType(SkipStrategy::class)
    fun setIndexValueWithAnimation(indexValue: Int)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setIndexValueWithOutAnimation(indexValue: Int)

    @StateStrategyType(SkipStrategy::class)
    fun showToastNoNetworkConnection()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setTextResult(indexClassification: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setTextDate(date: String)
}
