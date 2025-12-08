package com.example.recipeapp.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeDto(
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("cuisine")
    val cuisine: String,
    @SerialName("query")
    val query: String,
    @SerialName("imageUrl")
    val imageUrl: String?,
    @SerialName("sourceName")
    val sourceName: String,
    @SerialName("url")
    val url: String
)
