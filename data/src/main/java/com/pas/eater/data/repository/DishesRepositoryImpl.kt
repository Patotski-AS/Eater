package com.pas.eater.data.repository

import com.pas.eater.data.data_sourse.api.ApiService
import com.pas.eater.data.data_sourse.api.helper.RemoteException
import com.pas.eater.data.data_sourse.db.dao.DishesDao
import com.pas.eater.data.mappers.ErrorMapper
import com.pas.eater.data.mappers.MapDishes
import com.pas.eater.domain.models.Dish
import com.pas.eater.domain.repository.DishesRepository
import com.pas.eater.domain.util.Record
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DishesRepositoryImpl(
    private val api: ApiService,
    private val db: DishesDao,
    private val mapDishes: MapDishes,
    private val errorMapper: ErrorMapper
) : DishesRepository {

    override suspend fun getDishesFromApi(): Record<List<Dish>> {
        return try {
            api.getDishes().dishes.run {
                Record(mapDishes.dishesPojoToDish.mapList(this), null)
            }
        } catch (e: RemoteException) {
            errorMapper.mapErrorRecord(e)
        }
    }

    override suspend fun insertDishes(categories: List<Dish>) {
        db.updateDishes(mapDishes.dishToDisheEntity.mapList(categories))
    }

    override fun getDishesFromDB(): Flow<List<Dish>> {
        return db.getRDishes().map { mapDishes.disheEntityToDish.mapList(it) }
    }

}
