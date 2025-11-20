package com.example.recipeapp.domain.usecase

import com.example.recipeapp.domain.repository.MainRecipeRepository
import javax.inject.Inject

class UpdateSubscribedRecipesForAllCategoriesUseCase @Inject constructor(
    private val repository: MainRecipeRepository
) {
    suspend operator fun invoke() {
        repository.updateSubscribedRecipesForAllCategories()
    }
}