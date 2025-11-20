package com.example.recipeapp.domain.usecase

import com.example.recipeapp.domain.repository.MainRecipeRepository
import javax.inject.Inject

class ClearAllCategoriesUseCase @Inject constructor(
    private val repository: MainRecipeRepository
) {
    suspend operator fun invoke(categories: List<String>) {
        repository.clearAllCategories(categories)
    }
}