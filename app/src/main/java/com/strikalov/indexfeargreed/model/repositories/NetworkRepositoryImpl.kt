package com.strikalov.indexfeargreed.model.repositories

import com.strikalov.indexfeargreed.model.entity.FearGreedIndex
import com.strikalov.indexfeargreed.model.entity.FearGreedIndexList
import com.strikalov.indexfeargreed.model.network.WebApi
import io.reactivex.Observable

class NetworkRepositoryImpl(val webApi: WebApi):NetworkRepository{

    override fun loadFearGreedIndex(): Observable<FearGreedIndex> {
        return webApi.loadFearGreedIndexList().map {
            return@map it.fearGreedIndexList[0]
        }
    }

    override fun loadFearGreedIndexList(limit: Int): Observable<FearGreedIndexList> {
        return webApi.loadFearGreedIndexList(limit.toString())
    }
}