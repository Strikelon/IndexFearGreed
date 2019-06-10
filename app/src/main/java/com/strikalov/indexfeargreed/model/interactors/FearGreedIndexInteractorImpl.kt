package com.strikalov.indexfeargreed.model.interactors

import com.strikalov.indexfeargreed.model.entity.FearGreedIndex
import com.strikalov.indexfeargreed.model.entity.FearGreedIndexList
import com.strikalov.indexfeargreed.model.repositories.NetworkRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class FearGreedIndexInteractorImpl(val networkRepository: NetworkRepository): FearGreedIndexInteractor {

    override fun loadFearGreedIndex(): Observable<FearGreedIndex> {
        return networkRepository.loadFearGreedIndex()
            .subscribeOn(Schedulers.io())
    }

    override fun loadFearGreedIndexList(limit: Int): Observable<FearGreedIndexList> {
        return networkRepository.loadFearGreedIndexList(limit)
            .subscribeOn(Schedulers.io())
    }
}