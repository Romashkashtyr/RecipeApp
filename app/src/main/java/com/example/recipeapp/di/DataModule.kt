package com.example.recipeapp.di

import android.content.Context
import androidx.room.Room
import com.example.recipeapp.data.local.RecipeDao
import com.example.recipeapp.data.local.RecipeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    companion object {

        @Provides
        @Singleton
        fun provideRecipeDatabase(
            @ApplicationContext context: Context
        ): RecipeDatabase {
            return Room.databaseBuilder(
                context = context,
                klass = RecipeDatabase::class.java,
                name = "recipe.db"
            ).fallbackToDestructiveMigration(true).build()
        }

        @Provides
        @Singleton
        fun provideRecipeDao(
            database: RecipeDatabase
        ): RecipeDao = database.recipeDao()
    }
}