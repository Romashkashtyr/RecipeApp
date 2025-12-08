package com.example.recipeapp.di

import android.content.Context
import androidx.room.Room
import com.example.recipeapp.data.local.RecipeDao
import com.example.recipeapp.data.local.RecipeDatabase
import com.example.recipeapp.data.remote.RecipeApiService
import com.example.recipeapp.data.repository.MainRecipeRepositoryImpl
import com.example.recipeapp.domain.repository.MainRecipeRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Converter
import retrofit2.Converter.Factory
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindMainRecipeRepository(impl: MainRecipeRepositoryImpl): MainRecipeRepository

    companion object {


        @Provides
        @Singleton
        fun provideJson(): Json {
            return Json{
                ignoreUnknownKeys = true
                coerceInputValues = true
            }
        }

        @Provides
        @Singleton
        fun provideConverterFactory(
            json: Json
        ): Converter.Factory {
            return  json.asConverterFactory(
                "application/json".toMediaType()
            )
        }


        @Provides
        @Singleton
        fun provideRetrofit(
            converterFactory: Factory
        ): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://api.spoonacular.com/")
                .addConverterFactory(converterFactory)
                .build()
        }


        @Provides
        @Singleton
        fun provideApiService(
            retrofit: Retrofit
        ): RecipeApiService {
            return retrofit.create()
        }

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