package com.pas.eater.domain.repository

import com.pas.eater.domain.models.Dish
import com.pas.eater.domain.util.Record
import kotlinx.coroutines.flow.Flow

interface DishesRepository {
    suspend fun getDishesFromApi(): Record<List<Dish>>
    suspend fun insertDishes(categories: List<Dish>)
    fun getDishesFromDB(): Flow<List<Dish>>
}
