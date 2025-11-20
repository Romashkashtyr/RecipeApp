package com.example.recipeapp.domain.repository

import com.example.recipeapp.domain.entity.Recipe
import kotlinx.coroutines.flow.Flow

interface MainRecipeRepository {

    fun getAllRecipes(): Flow<List<String>>

    suspend fun addCategory(category: String)

    suspend fun updateRecipeForTopic(recipeName: String)

    suspend fun removeCategory(category: String)

    suspend fun updateSubscribedRecipesForAllCategories()

    fun getRecipesByCategory(category: List<String>): Flow<List<Recipe>>

    suspend fun clearAllCategories(categories: List<String>)
}