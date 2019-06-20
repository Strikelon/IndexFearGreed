package com.strikalov.indexfeargreed.presenter

import com.strikalov.indexfeargreed.model.entity.ChartData
import com.strikalov.indexfeargreed.model.interactors.FearGreedIndexInteractor
import com.strikalov.indexfeargreed.view.ChartFragmentView
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class ChartFragmentPresenterTest {

    lateinit var chartFragmentPresenter: ChartFragmentPresenter

    @Mock
    lateinit var chartFragmentView: ChartFragmentView

    @Mock
    lateinit var chartFragmentView2: ChartFragmentView

    @Mock
    lateinit var fearGreedIndexInteractor: FearGreedIndexInteractor

    @Before
    fun setUp(){

        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        chartFragmentPresenter = ChartFragmentPresenter(fearGreedIndexInteractor)

        chartFragmentPresenter.attachView(chartFragmentView)

    }

    @Test
    fun attachViewTest(){
        chartFragmentPresenter.attachView(chartFragmentView2)

        Mockito.verify(chartFragmentView2).chartHide()
        Mockito.verify(chartFragmentView2).chartDescriptionHide()
        Mockito.verify(chartFragmentView2).noNetworkConnectionMessageHide()
        Mockito.verify(chartFragmentView2).swipeRefreshShow()
        Mockito.verify(chartFragmentView2).isOnline()
    }

    @Test
    fun networkNotConnectedTest(){
        chartFragmentPresenter.networkNotConnected()

        Mockito.verify(chartFragmentView).swipeRefreshHide()
        Mockito.verify(chartFragmentView).showToastNoNetworkConnection()
        Mockito.verify(chartFragmentView).noNetworkConnectionMessageShow()

    }

    @Test
    fun networkIsConnectedTest(){
        val chartDataLimit = 30
        val date1 = Date()
        val date2 = Date()
        val chartDataList: MutableList<ChartData> = ArrayList()
        chartDataList.add(ChartData(50, date1))
        chartDataList.add(ChartData(25, date2))

        Mockito.`when`(fearGreedIndexInteractor.loadFearGreedIndexList(chartDataLimit)).thenReturn(Observable.just(chartDataList))

        chartFragmentPresenter.networkIsConnected()

        Mockito.verify(chartFragmentView).setValuesInGraphAndShowChart(chartDataList)
        Mockito.verify(chartFragmentView).chartDescriptionShow()
        Mockito.verify(chartFragmentView).swipeRefreshHide()

    }

    @Test
    fun onSwipeRefreshFearGreedIndex_chartDataListNull_test(){

        chartFragmentPresenter.onSwipeRefreshFearGreedIndex()

        Mockito.verify(chartFragmentView,times(2)).isOnline()

    }

    @Test
    fun onSwipeRefreshFearGreedIndex_chartDataListNotNull_test(){

        val chartDataLimit = 30
        val date1 = Date()
        val date2 = Date()
        val chartDataList: MutableList<ChartData> = ArrayList()
        chartDataList.add(ChartData(50, date1))
        chartDataList.add(ChartData(25, date2))

        Mockito.`when`(fearGreedIndexInteractor.loadFearGreedIndexList(chartDataLimit)).thenReturn(Observable.just(chartDataList))

        chartFragmentPresenter.networkIsConnected()
        chartFragmentPresenter.onSwipeRefreshFearGreedIndex()

        Mockito.verify(chartFragmentView,times(2)).setValuesInGraphAndShowChart(chartDataList)
        Mockito.verify(chartFragmentView, times(2)).chartDescriptionShow()
        Mockito.verify(chartFragmentView, times(2)).swipeRefreshHide()

    }

}