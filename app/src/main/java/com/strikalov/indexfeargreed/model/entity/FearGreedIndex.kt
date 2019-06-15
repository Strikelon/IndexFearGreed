package com.strikalov.indexfeargreed.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FearGreedIndex(

    @Expose
    @SerializedName("value")
    val value: String,

    @Expose
    @SerializedName("value_classification")
    val valueClassification: String,

    @Expose
    @SerializedName("timestamp")
    val timestamp: Long
)