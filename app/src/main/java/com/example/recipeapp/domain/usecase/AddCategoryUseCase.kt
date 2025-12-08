package com.example.recipeapp.domain.usecase


import com.example.recipeapp.domain.repository.MainRecipeRepository
import javax.inject.Inject

class AddCategoryUseCase @Inject constructor(
    private val repository: MainRecipeRepository
) {
    suspend operator fun invoke(category: String, cuisine: String) {
        repository.addSubscription(category, cuisine)
        repository.updateRecipeForTopic(category, cuisine)
    }
}