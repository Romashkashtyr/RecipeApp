package com.example.recipeapp.domain.usecase

import com.example.recipeapp.domain.repository.MainRecipeRepository
import javax.inject.Inject

class GetAllRecipesUseCase @Inject constructor(
    private val repository: MainRecipeRepository
) {

    operator fun invoke() = repository.getAllSubscriptions()
}