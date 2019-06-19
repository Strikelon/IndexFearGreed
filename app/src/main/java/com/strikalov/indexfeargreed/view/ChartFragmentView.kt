package com.strikalov.indexfeargreed.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.strikalov.indexfeargreed.model.entity.ChartData

interface ChartFragmentView : MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun swipeRefreshShow()

    @StateStrategyType(SkipStrategy::class)
    fun swipeRefreshHide()

    @StateStrategyType(SkipStrategy::class)
    fun isOnline()

    @StateStrategyType(SkipStrategy::class)
    fun setValuesInGraphAndShowChart(chartDataList: MutableList<ChartData>)

    @StateStrategyType(SkipStrategy::class)
    fun chartHide()

    @StateStrategyType(SkipStrategy::class)
    fun chartDescriptionShow()

    @StateStrategyType(SkipStrategy::class)
    fun chartDescriptionHide()

    @StateStrategyType(SkipStrategy::class)
    fun noNetworkConnectionMessageShow()

    @StateStrategyType(SkipStrategy::class)
    fun noNetworkConnectionMessageHide()

    @StateStrategyType(SkipStrategy::class)
    fun showToastNoNetworkConnection()

}