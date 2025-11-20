package com.example.recipeapp.domain.usecase

import com.example.recipeapp.domain.entity.Recipe
import com.example.recipeapp.domain.repository.MainRecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecipesByCategoryUseCase @Inject constructor(
    private val repository: MainRecipeRepository
) {
     operator fun invoke(categories: List<String>): Flow<List<Recipe>> {
        return repository.getRecipesByCategory(categories)
    }
}