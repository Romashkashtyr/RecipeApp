package com.example.recipeapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.recipeapp.data.remote.Cuisine

@Entity(
    tableName = "subscriptions"
)
data class SubscriptionDbModel(@PrimaryKey val category: String, val cuisine: String)
