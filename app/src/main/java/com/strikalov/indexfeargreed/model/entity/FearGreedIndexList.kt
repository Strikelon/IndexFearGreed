package com.strikalov.indexfeargreed.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FearGreedIndexList(

    @Expose
    @SerializedName("data")
    val fearGreedIndexList: List<FearGreedIndex>
)