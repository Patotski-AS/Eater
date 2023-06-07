package com.pas.eater.domain.repository

import com.pas.eater.domain.models.Category
import com.pas.eater.domain.util.Record
import kotlinx.coroutines.flow.Flow


interface CategoriesRepository {
    suspend fun getCategoriesFromApi(): Record<List<Category>>
    suspend fun insertCategories(categories: List<Category>)
    fun getCategoriesFromDB(): Flow<List<Category>>
}