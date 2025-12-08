package com.example.recipeapp.domain.entity

data class Recipe (
    val title: String,
    val description: String,
    val cuisine: String,
    val query: String,
    val imageUrl: String?,
    val sourceName: String,
    val url: String
)
