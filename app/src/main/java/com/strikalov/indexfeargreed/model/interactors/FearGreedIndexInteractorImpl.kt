package com.strikalov.indexfeargreed.model.interactors

import com.strikalov.indexfeargreed.model.entity.ChartData
import com.strikalov.indexfeargreed.model.entity.FearGreedIndex
import com.strikalov.indexfeargreed.model.entity.FearGreedIndexList
import com.strikalov.indexfeargreed.model.repositories.NetworkRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.*

class FearGreedIndexInteractorImpl(val networkRepository: NetworkRepository): FearGreedIndexInteractor {

    override fun loadFearGreedIndex(): Observable<FearGreedIndex> {
        return networkRepository.loadFearGreedIndex()
            .subscribeOn(Schedulers.io())
    }

    override fun loadFearGreedIndexList(limit: Int): Observable<MutableList<ChartData>> {
        return networkRepository.loadFearGreedIndexList(limit)
            .map {

                val chartDataList: MutableList<ChartData> = ArrayList()
                val calendar = Calendar.getInstance()

                for (fearGreedIndex in it.fearGreedIndexList) {

                    val indexValue: Int = Integer.parseInt(fearGreedIndex.value)
                    val timestamp: Long = fearGreedIndex.timestamp
                    calendar.timeInMillis = timestamp * 1000
                    val date: Date = calendar.time
                    
                    chartDataList.add(ChartData(indexValue, date))
                }

                return@map chartDataList
            }
            .subscribeOn(Schedulers.io())
    }
}