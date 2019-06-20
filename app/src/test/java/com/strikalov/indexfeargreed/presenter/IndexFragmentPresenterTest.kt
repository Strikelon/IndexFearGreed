package com.strikalov.indexfeargreed.presenter

import com.strikalov.indexfeargreed.model.entity.FearGreedIndex
import com.strikalov.indexfeargreed.model.interactors.FearGreedIndexInteractor
import com.strikalov.indexfeargreed.view.IndexFragmentView
import io.reactivex.Observable
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.plugins.RxAndroidPlugins
import org.junit.Test
import org.mockito.Mockito
import java.text.SimpleDateFormat
import java.util.*


@RunWith(MockitoJUnitRunner::class)
class IndexFragmentPresenterTest {

    lateinit var indexFragmentPresenter: IndexFragmentPresenter

    @Mock
    lateinit var indexFragmentView: IndexFragmentView

    @Mock
    lateinit var fearGreedIndexInteractor: FearGreedIndexInteractor

    private val dateFormat = SimpleDateFormat("dd/MM/yyyy")

    @Before
    fun setUp(){

        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        indexFragmentPresenter = IndexFragmentPresenter(fearGreedIndexInteractor)
        indexFragmentPresenter.attachView(indexFragmentView)
    }

    @Test
    fun onSwipeRefreshFearGreedIndexIsAnimatorRunningTrueTest(){

        indexFragmentPresenter.onSwipeRefreshFearGreedIndex(true)

        Mockito.verify(indexFragmentView).swipeRefreshHide()
    }

    @Test
    fun onSwipeRefreshFearGreedIndexIsAnimatorRunningFalseTest(){

        indexFragmentPresenter.onSwipeRefreshFearGreedIndex(false)

        Mockito.verify(indexFragmentView).isOnline()

    }

    @Test
    fun onClickRefreshFearGreedIndexTrueTest(){

        indexFragmentPresenter.onClickRefreshFearGreedIndex(true)

        Mockito.verify(indexFragmentView, Mockito.never()).swipeRefreshShow()
        Mockito.verify(indexFragmentView, Mockito.never()).isOnline()
    }

    @Test
    fun onClickRefreshFearGreedIndexFalseTest(){

        indexFragmentPresenter.onClickRefreshFearGreedIndex(false)

        Mockito.verify(indexFragmentView).swipeRefreshShow()
        Mockito.verify(indexFragmentView).isOnline()

    }

    @Test
    fun networkNotConnectedTest(){

        indexFragmentPresenter.networkNotConnected()

        Mockito.verify(indexFragmentView).swipeRefreshHide()
        Mockito.verify(indexFragmentView).showToastNoNetworkConnection()

    }

    @Test
    fun networkIsConnectedTest(){

        val fearGreedIndexValue = 81
        val valueClassification = "Extreme Greed"
        val timeStamp: Long = 1561003200
        val fearGreedIndex = FearGreedIndex(fearGreedIndexValue.toString(), valueClassification, timeStamp)

        Mockito.`when`(fearGreedIndexInteractor.loadFearGreedIndex()).thenReturn(Observable.just(fearGreedIndex))

        indexFragmentPresenter.networkIsConnected()

        Mockito.verify(indexFragmentView).swipeRefreshHide()
        Mockito.verify(indexFragmentView).setIndexValueWithAnimation(fearGreedIndexValue)

    }

    @Test
    fun onAnimationEndGreedTest(){

        val fearGreedIndexValue = 81
        val valueClassification = "Extreme Greed"
        val timeStamp: Long = 1561003200
        val date = Date(timeStamp * 1000)
        val fearGreedIndex = FearGreedIndex(fearGreedIndexValue.toString(), valueClassification, timeStamp)

        Mockito.`when`(fearGreedIndexInteractor.loadFearGreedIndex()).thenReturn(Observable.just(fearGreedIndex))

        indexFragmentPresenter.networkIsConnected()
        indexFragmentPresenter.onAnimationEnd()

        Mockito.verify(indexFragmentView).setTextResult(IndexFragmentPresenter.GREED)
        Mockito.verify(indexFragmentView).setIndexValueWithOutAnimation(fearGreedIndexValue)
        Mockito.verify(indexFragmentView).setTextDate(dateFormat.format(date))

    }

    @Test
    fun onAnimationEndNeutralTest(){

        val fearGreedIndexValue = 50
        val valueClassification = "Neutral"
        val timeStamp: Long = 1561003200
        val date = Date(timeStamp * 1000)
        val fearGreedIndex = FearGreedIndex(fearGreedIndexValue.toString(), valueClassification, timeStamp)

        Mockito.`when`(fearGreedIndexInteractor.loadFearGreedIndex()).thenReturn(Observable.just(fearGreedIndex))

        indexFragmentPresenter.networkIsConnected()
        indexFragmentPresenter.onAnimationEnd()

        Mockito.verify(indexFragmentView).setTextResult(IndexFragmentPresenter.NEUTRAL)
        Mockito.verify(indexFragmentView).setIndexValueWithOutAnimation(fearGreedIndexValue)
        Mockito.verify(indexFragmentView).setTextDate(dateFormat.format(date))

    }

    @Test
    fun onAnimationEndFearTest(){

        val fearGreedIndexValue = 20
        val valueClassification = "Fear"
        val timeStamp: Long = 1561003200
        val date = Date(timeStamp * 1000)

        val fearGreedIndex = FearGreedIndex(fearGreedIndexValue.toString(), valueClassification, timeStamp)

        Mockito.`when`(fearGreedIndexInteractor.loadFearGreedIndex()).thenReturn(Observable.just(fearGreedIndex))

        indexFragmentPresenter.networkIsConnected()
        indexFragmentPresenter.onAnimationEnd()

        Mockito.verify(indexFragmentView).setTextResult(IndexFragmentPresenter.FEAR)
        Mockito.verify(indexFragmentView).setIndexValueWithOutAnimation(fearGreedIndexValue)
        Mockito.verify(indexFragmentView).setTextDate(dateFormat.format(date))
    }

}