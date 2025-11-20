package com.example.recipeapp.data.repository

import com.example.recipeapp.domain.entity.Recipe
import com.example.recipeapp.domain.repository.MainRecipeRepository
import kotlinx.coroutines.flow.Flow

class MainRecipeRepositoryImpl : MainRecipeRepository {
    override fun getAllRecipes(): Flow<List<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun addCategory(category: String) {
        TODO("Not yet implemented")
    }

    override suspend fun updateRecipeForTopic(recipeName: String) {
        TODO("Not yet implemented")
    }

    override suspend fun removeCategory(category: String) {
        TODO("Not yet implemented")
    }

    override suspend fun updateSubscribedRecipesForAllCategories() {
        TODO("Not yet implemented")
    }

    override fun getRecipesByCategory(category: List<String>): Flow<List<Recipe>> {
        TODO("Not yet implemented")
    }

    override suspend fun clearAllCategories(categories: List<String>) {
        TODO("Not yet implemented")
    }
}