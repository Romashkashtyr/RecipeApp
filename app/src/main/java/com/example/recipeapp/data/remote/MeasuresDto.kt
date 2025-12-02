package com.example.recipeapp.data.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MeasuresDto(
    @SerialName("metric")
    val metricDto: MetricDto = MetricDto(),
)