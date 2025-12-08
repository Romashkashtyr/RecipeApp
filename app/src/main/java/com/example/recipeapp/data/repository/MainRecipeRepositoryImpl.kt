package com.example.recipeapp.data.repository

import android.util.Log
import com.example.recipeapp.data.local.RecipeDao
import com.example.recipeapp.data.local.RecipeDbModel
import com.example.recipeapp.data.local.SubscriptionDbModel
import com.example.recipeapp.data.mapper.toDbModels
import com.example.recipeapp.data.mapper.toEntities
import com.example.recipeapp.data.remote.Cuisine
import com.example.recipeapp.data.remote.RecipeApiService
import com.example.recipeapp.domain.entity.Recipe
import com.example.recipeapp.domain.repository.MainRecipeRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainRecipeRepositoryImpl @Inject constructor(
    private val recipeDao: RecipeDao,
    private val recipeApiService: RecipeApiService
) : MainRecipeRepository {


    override fun getAllSubscriptions(): Flow<List<String>> {
        return recipeDao.getAllSubscriptions().map { subscriptions ->
            subscriptions.map { it.category }
        }
    }

    override suspend fun addSubscription(category: String, cuisine: String) {
        recipeDao.addSubscription(SubscriptionDbModel(category, cuisine))
    }

    override suspend fun updateRecipeForTopic(recipeName: String, cuisine: String) {
        val recipes = loadRecipes(recipeName, cuisine)
        recipeDao.addRecipes(recipes)
    }

    private suspend fun loadRecipes(category: String, cuisine: String): List<RecipeDbModel> {
        return try {
            recipeApiService.loadRecipes(category, cuisine).toDbModels(category)
        } catch (e: Exception) {
            if (e is CancellationException) {
                throw e
            }
            Log.e("MainRecipeRepository", e.stackTraceToString())
            listOf()
        }

    }

    override suspend fun removeCategory(category: String, cuisine: String) {
        recipeDao.deleteSubscription(SubscriptionDbModel(category, cuisine))
    }

    override suspend fun updateSubscribedRecipesForAllSubscriptions() {
        val subscriptions = recipeDao.getAllSubscriptions().first()
        coroutineScope {
            subscriptions.forEach {
                launch {
                    updateRecipeForTopic(it.category, it.cuisine)
                }
            }
        }
    }

    override fun getRecipesByCategory(category: List<String>): Flow<List<Recipe>> {
        return recipeDao.getAllRecipesByCategories(category).map {
            it.toEntities()
        }
    }

    override suspend fun clearAllCategories(categories: List<String>) {
        recipeDao.deleteRecipesByCategories(categories)
    }
}