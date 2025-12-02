package com.example.recipeapp.data.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MetricDto(
    @SerialName("amount")
    val amount: Double = 0.0,
    @SerialName("unitLong")
    val unitLong: String = "",
    @SerialName("unitShort")
    val unitShort: String = ""
)