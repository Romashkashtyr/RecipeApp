package com.example.recipeapp.domain.repository

import com.example.recipeapp.domain.entity.Recipe
import kotlinx.coroutines.flow.Flow

interface MainRecipeRepository {

    fun getAllSubscriptions(): Flow<List<String>>

    suspend fun addSubscription(category: String, cuisine: String)

    suspend fun updateRecipeForTopic(recipeName: String, cuisine: String)

    suspend fun removeCategory(category: String, cuisine: String)

    suspend fun updateSubscribedRecipesForAllSubscriptions()

    fun getRecipesByCategory(category: List<String>): Flow<List<Recipe>>

    suspend fun clearAllCategories(categories: List<String>)
}