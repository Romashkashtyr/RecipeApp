package com.example.recipeapp.data.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "recipes",
    primaryKeys = ["url", "category"],
    foreignKeys = [
        ForeignKey(
            entity = SubscriptionDbModel::class,
            parentColumns = ["category"],
            childColumns = ["category"],
            onDelete = ForeignKey.CASCADE
        ),
    ],
    indices = [Index("category")]

)
data class RecipeDbModel(
    val title: String,
    val description: String,
    val cuisine: String,
    val query: String,
    val imageUrl: String?,
    val sourceName: String,
    val url: String,
    val category: String
)
