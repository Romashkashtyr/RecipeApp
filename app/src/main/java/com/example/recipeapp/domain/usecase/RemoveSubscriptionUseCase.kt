package com.example.recipeapp.domain.usecase

import com.example.recipeapp.domain.repository.MainRecipeRepository
import javax.inject.Inject

class RemoveSubscriptionUseCase @Inject constructor(
    private val repository: MainRecipeRepository
) {
    suspend operator fun invoke(category: String) {
        repository.removeCategory(category)
    }
}