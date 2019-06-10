package com.strikalov.indexfeargreed.model.interactors

import com.strikalov.indexfeargreed.model.entity.FearGreedIndex
import com.strikalov.indexfeargreed.model.entity.FearGreedIndexList
import io.reactivex.Observable

interface FearGreedIndexInteractor {

    fun loadFearGreedIndex(): Observable<FearGreedIndex>

    fun loadFearGreedIndexList(limit: Int): Observable<FearGreedIndexList>

}