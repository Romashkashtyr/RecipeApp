package com.example.recipeapp.data.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExtendedIngredient(
    @SerialName("aisle")
    val aisle: String = "",
    @SerialName("amount")
    val amount: Double = 0.0,
    @SerialName("image")
    val image: String = "",
    @SerialName("measures")
    val measuresDto: MeasuresDto = MeasuresDto(),
    @SerialName("name")
    val name: String = "",
    @SerialName("nameClean")
    val nameClean: String = "",
)