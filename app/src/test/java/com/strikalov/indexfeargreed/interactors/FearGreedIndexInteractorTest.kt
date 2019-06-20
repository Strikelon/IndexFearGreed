package com.strikalov.indexfeargreed.interactors

import com.strikalov.indexfeargreed.model.entity.ChartData
import com.strikalov.indexfeargreed.model.entity.FearGreedIndex
import com.strikalov.indexfeargreed.model.entity.FearGreedIndexList
import com.strikalov.indexfeargreed.model.interactors.FearGreedIndexInteractor
import com.strikalov.indexfeargreed.model.interactors.FearGreedIndexInteractorImpl
import com.strikalov.indexfeargreed.model.repositories.NetworkRepository
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class FearGreedIndexInteractorTest {

    lateinit var fearGreedIndexInteractor: FearGreedIndexInteractor

    @Mock
    lateinit var networkRepository: NetworkRepository

    @Before
    fun setUp(){

        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        fearGreedIndexInteractor = FearGreedIndexInteractorImpl(networkRepository)

    }

    @Test
    fun loadFearGreedIndexTest(){

        val fearGreedIndexValue = 81
        val valueClassification = "Extreme Greed"
        val timeStamp: Long = 1561003200
        val fearGreedIndex = FearGreedIndex(fearGreedIndexValue.toString(), valueClassification, timeStamp)

        Mockito.`when`(networkRepository.loadFearGreedIndex()).thenReturn(Observable.just(fearGreedIndex))

        val testObserver = TestObserver<FearGreedIndex>()

        fearGreedIndexInteractor.loadFearGreedIndex().subscribe(testObserver)

        testObserver.await()
        testObserver.assertValue(fearGreedIndex)

    }

    @Test
    fun loadFearGreedIndexList(){

        val limit = 2

        val fearGreedIndexValue = 81
        val valueClassification = "Extreme Greed"
        val timeStamp: Long = 1561003200
        val fearGreedIndex = FearGreedIndex(fearGreedIndexValue.toString(), valueClassification, timeStamp)
        val fearGreedIndexL: List<FearGreedIndex> = listOf(fearGreedIndex,fearGreedIndex)

        val fearGreedIndexList = FearGreedIndexList(fearGreedIndexL)

        Mockito.`when`(networkRepository.loadFearGreedIndexList(limit)).thenReturn(Observable.just(fearGreedIndexList))

        val chartDataList: MutableList<ChartData> = ArrayList()
        val calendar = Calendar.getInstance()

        for (fearGreedId in fearGreedIndexL) {

            val indexValue: Int = Integer.parseInt(fearGreedId.value)
            val timestamp: Long = fearGreedId.timestamp
            calendar.timeInMillis = timestamp * 1000
            val date: Date = calendar.time

            chartDataList.add(ChartData(indexValue, date))
        }

        val testObserver = TestObserver<MutableList<ChartData>>()

        fearGreedIndexInteractor.loadFearGreedIndexList(limit).subscribe(testObserver)

        testObserver.await()
        testObserver.assertValue(chartDataList)

    }

}