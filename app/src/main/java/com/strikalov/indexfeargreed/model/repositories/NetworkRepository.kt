package com.strikalov.indexfeargreed.model.repositories

import com.strikalov.indexfeargreed.model.entity.FearGreedIndex
import com.strikalov.indexfeargreed.model.entity.FearGreedIndexList
import io.reactivex.Observable

interface NetworkRepository {

    fun loadFearGreedIndex(): Observable<FearGreedIndex>

    fun loadFearGreedIndexList(limit: Int): Observable<FearGreedIndexList>

}