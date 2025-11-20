package com.example.recipeapp.domain.entity

data class Recipe (
    val title: String,
    val description: String,
    val imageUrl: String?,
    val sourceName: String,
    val publishedAt: Long,
    val url: String
)
