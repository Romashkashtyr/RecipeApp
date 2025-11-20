package com.example.recipeapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Query("SELECT * FROM subscriptions")
    fun getAllSubscriptions(): Flow<List<SubscriptionDbModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSubscription(subscriptionDbModel: SubscriptionDbModel)

    @Transaction
    @Delete
    suspend fun deleteSubscription(subscriptionDbModel: SubscriptionDbModel)

    @Query("SELECT * FROM recipes WHERE category IN (:categories) ORDER BY publishedAt DESC")
    fun getAllRecipesByCategories(categories: List<String>): Flow<List<RecipeDbModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRecipes(recipes: List<RecipeDbModel>)

    @Query("DELETE FROM recipes WHERE category IN (:categories)")
    suspend fun deleteRecipesByCategories(categories: List<String>)
}