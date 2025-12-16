package com.example.recipeapp.presentation.screen.subscriptions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.domain.entity.Recipe
import com.example.recipeapp.domain.usecase.AddSubscriptionUseCase
import com.example.recipeapp.domain.usecase.ClearAllCategoriesUseCase
import com.example.recipeapp.domain.usecase.GetAllRecipesUseCase
import com.example.recipeapp.domain.usecase.GetRecipesByCategoryUseCase
import com.example.recipeapp.domain.usecase.RemoveSubscriptionUseCase
import com.example.recipeapp.domain.usecase.UpdateSubscribedRecipesForAllCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubscriptionsViewModel @Inject constructor(
    private val addSubscriptionUseCase: AddSubscriptionUseCase,
    private val clearAllCategoriesUseCase: ClearAllCategoriesUseCase,
    private val getAllRecipesUseCase: GetAllRecipesUseCase,
    private val getRecipesByCategoryUseCase: GetRecipesByCategoryUseCase,
    private val removeSubscriptionUseCase: RemoveSubscriptionUseCase,
    private val updateSubscribedRecipesForAllCategoriesUseCase: UpdateSubscribedRecipesForAllCategoriesUseCase
): ViewModel() {

    private val _state = MutableStateFlow(SubscriptionsState())
    val state = _state.asStateFlow()


    fun processCommand(command: SubscriptionsCommand) {
        when(command) {
            SubscriptionsCommand.ClearRecipes -> {
                viewModelScope.launch {
                    val categories = state.value.selectedCategories
                    clearAllCategoriesUseCase(categories)
                }
            }
            SubscriptionsCommand.ClickSubscribe -> {
                viewModelScope.launch {
                    _state.update { previousState ->
                        val categoryQuery = state.value.query.trim()
                        val  categoryCuisine = state.value.cuisine.trim()
                        addSubscriptionUseCase(categoryQuery, categoryCuisine)
                        previousState.copy(query = categoryQuery, cuisine = categoryCuisine)
                    }
                }
            }
            is SubscriptionsCommand.InputCategory -> {
                _state.update { previousState ->
                    previousState.copy(query = command.query)
                }
            }
            SubscriptionsCommand.RefreshData -> {
                viewModelScope.launch {
                    updateSubscribedRecipesForAllCategoriesUseCase()
                }
            }
            is SubscriptionsCommand.RemoveSubscription -> {
                viewModelScope.launch {
                    removeSubscriptionUseCase(command.category, command.cuisine)
                }
            }
            is SubscriptionsCommand.ToggleCategorySelection -> {
                _state.update { previousState ->
                    val subscriptions = previousState.subscriptions.toMutableMap()
                    val isSelected = subscriptions[command.category] ?: false
                    subscriptions[command.category]= !isSelected
                    previousState.copy(subscriptions = subscriptions)
                }
            }
        }
    }
}

sealed interface SubscriptionsCommand {

    data class InputCategory(val query: String): SubscriptionsCommand

    data object ClickSubscribe: SubscriptionsCommand

    data object RefreshData: SubscriptionsCommand

    data class ToggleCategorySelection(val category: String): SubscriptionsCommand

    data object ClearRecipes: SubscriptionsCommand

    data class RemoveSubscription(val category: String, val cuisine: String): SubscriptionsCommand
}


data class SubscriptionsState(
    val query: String = "",
    val cuisine: String = "",
    val subscriptions: Map<String, Boolean> = mapOf(),
    val recipes: List<Recipe> = listOf()
) {


    val subscribedButtonEnabled: Boolean
        get() = query.isNotBlank()

    val selectedCategories: List<String>
        get() = subscriptions.filter { it.value }.map { it.key }

}
