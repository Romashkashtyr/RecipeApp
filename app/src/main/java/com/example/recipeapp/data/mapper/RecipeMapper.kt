package com.example.recipeapp.data.mapper

import com.example.recipeapp.data.local.RecipeDbModel
import com.example.recipeapp.data.remote.RecipeResponseDto
import com.example.recipeapp.domain.entity.Recipe

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

fun List<RecipeDbModel>.toEntities(): List<Recipe> {
    return map {
        Recipe(
            title = it.title,
            description = it.description,
            cuisine = it.cuisine,
            query = it.query,
            imageUrl = it.imageUrl,
            sourceName = it.sourceName,
            url = it.url
        )
    }.distinct()
}