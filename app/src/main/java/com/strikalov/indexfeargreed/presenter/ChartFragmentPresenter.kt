package com.strikalov.indexfeargreed.presenter

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.strikalov.indexfeargreed.model.entity.ChartData
import com.strikalov.indexfeargreed.model.interactors.FearGreedIndexInteractor
import com.strikalov.indexfeargreed.view.ChartFragmentView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import javax.inject.Singleton

@InjectViewState
@Singleton
class ChartFragmentPresenter @Inject constructor(
    val fearGreedIndexInteractor: FearGreedIndexInteractor): MvpPresenter<ChartFragmentView>(){

    companion object {
        private const val TAG_LOG = "ChartFragmentPresenter"
        private const val CHART_DATA_LIMIT = 30
    }

    private var chartDataList: MutableList<ChartData>? = null

    private var disposable: Disposable? = null

    override fun attachView(view: ChartFragmentView?) {
        super.attachView(view)
        viewState.chartHide()
        viewState.chartDescriptionHide()
        viewState.noNetworkConnectionMessageHide()
        viewState.swipeRefreshShow()
        Log.i(TAG_LOG, chartDataList.toString())
        chartDataList?.let {
            viewState.setValuesInGraphAndShowChart(it)
            viewState.chartDescriptionShow()
            viewState.swipeRefreshHide()
        } ?: viewState.isOnline()

    }

    private fun downloadChartDataList(){

        Log.i(TAG_LOG, "downloadChartDataList()")

        disposable?.dispose()

        disposable = fearGreedIndexInteractor.loadFearGreedIndexList(CHART_DATA_LIMIT)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {

                    chartDataList = it
                    chartDataList?.let { list ->
                        list.reverse()
                        viewState.setValuesInGraphAndShowChart(list)
                        viewState.chartDescriptionShow()
                        viewState.swipeRefreshHide()
                    } ?: networkNotConnected()

                },
                {
                    Log.i(TAG_LOG, it.toString())
                    viewState.swipeRefreshHide()
                    viewState.showErrorToast(it.toString())
                }
            )


    }

    fun networkIsConnected(){
        downloadChartDataList()
    }

    fun networkNotConnected(){
        viewState.swipeRefreshHide()
        viewState.showToastNoNetworkConnection()
        viewState.noNetworkConnectionMessageShow()
    }

    fun onSwipeRefreshFearGreedIndex(){
        viewState.chartHide()
        viewState.chartDescriptionHide()
        viewState.noNetworkConnectionMessageHide()
        Log.i(TAG_LOG, chartDataList.toString())
        chartDataList?.let {
            viewState.setValuesInGraphAndShowChart(it)
            viewState.chartDescriptionShow()
            viewState.swipeRefreshHide()
        } ?: viewState.isOnline()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}