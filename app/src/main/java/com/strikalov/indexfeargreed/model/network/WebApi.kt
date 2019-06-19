package com.strikalov.indexfeargreed.model.network

import com.strikalov.indexfeargreed.model.entity.FearGreedIndexList
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class WebApi{

    companion object {
        private const val URL_REQUEST = "https://api.alternative.me/"
    }

    private var webApiService: WebApiService

    init {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(URL_REQUEST)
            .build()

        webApiService = retrofit.create(WebApiService::class.java)
    }

    fun loadFearGreedIndexList() = webApiService.loadFearGreedIndexList()

    fun loadFearGreedIndexList(limit: String) = webApiService.loadFearGreedIndexList(limit)

}