package com.strikalov.indexfeargreed.model.network

import com.strikalov.indexfeargreed.model.entity.FearGreedIndexList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WebApiService {

    @GET("fng")
    fun loadFearGreedIndexList(): Observable<FearGreedIndexList>

    @GET("fng/")
    fun loadFearGreedIndexList(@Query("limit") limit: String): Observable<FearGreedIndexList>
}