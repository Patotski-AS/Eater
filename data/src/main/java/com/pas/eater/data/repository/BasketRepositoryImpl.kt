package com.pas.eater.data.repository

import com.pas.eater.data.data_sourse.db.dao.BasketDao
import com.pas.eater.data.mappers.MapBasket
import com.pas.eater.domain.models.BasketItem
import com.pas.eater.domain.repository.BasketRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BasketRepositoryImpl(
    private val db: BasketDao,
    private val mapBasket: MapBasket,
): BasketRepository {


    override suspend fun insertBasketItem(item: BasketItem) {
        db.insertBasketItem(mapBasket.itemToEntity.map(item))
    }

    override suspend fun deleteBasketItem(id: Int) {
        db.deleteItem(id)
    }

    override suspend fun deleteBasket() {
        db.deleteBasket()
    }

    override fun getBasketFromDB(): Flow<List<BasketItem>> {
        return db.getBasket().map {mapBasket.entityToItem.mapList(it)}
    }
}
