package com.example.recipeapp.data.mapper

import com.example.recipeapp.data.local.RecipeDbModel
import com.example.recipeapp.data.remote.RecipeResponseDto

fun RecipeResponseDto.toDbModels(category: String): List<RecipeDbModel> {
    return recipes.map {
        RecipeDbModel(
            title = it.title,
            description = it.description,
            cuisine = it.cuisine,
            query = it.query,
            imageUrl = it.imageUrl,
            sourceName = it.sourceName,
            url = it.url,
            category = category
        )
    }
}