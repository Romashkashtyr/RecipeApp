package com.example.recipeapp.data.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeResponseDto(
    @SerialName("query")
    val query: String = "",
    @SerialName("cookingMinutes")
    val cookingMinutes: Int = 0,
    @SerialName("cuisines")
    val cuisines: List<Cuisine?> = listOf(),
    @SerialName("extendedIngredients")
    val extendedIngredients: List<ExtendedIngredient> = listOf(),
    @SerialName("image")
    val image: String = "",
    @SerialName("instructions")
    val instructions: String = "",
    @SerialName("pricePerServing")
    val pricePerServing: Double = 0.0,
    @SerialName("sourceName")
    val sourceName: String = "",
    @SerialName("sourceUrl")
    val sourceUrl: String = "",
    @SerialName("summary")
    val summary: String = "",
    @SerialName("title")
    val title: String = ""
)