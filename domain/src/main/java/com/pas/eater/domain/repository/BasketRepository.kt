package com.pas.eater.domain.repository

import com.pas.eater.domain.models.BasketItem
import kotlinx.coroutines.flow.Flow

interface BasketRepository {

    suspend fun insertBasketItem(item: BasketItem)
    suspend fun deleteBasketItem(id: Int)
    fun getBasketFromDB(): Flow<List<BasketItem>>
    suspend fun deleteBasket()
}
