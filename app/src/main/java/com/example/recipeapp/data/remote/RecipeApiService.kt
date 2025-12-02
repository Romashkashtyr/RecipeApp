package com.example.recipeapp.data.remote

import com.example.recipeapp.BuildConfig
import dagger.Provides
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

interface RecipeApiService {


    @GET("/recipes/complexSearch?&apiKey=${BuildConfig.RECIPE_API_KEY}")
    suspend fun loadRecipes(
        @Query("query") query: String,
        @Query("cuisine") cuisine: String
    ): RecipeResponseDto
}