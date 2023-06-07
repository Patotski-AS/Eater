package com.pas.eater.data.data_sourse.db.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import com.pas.eater.data.data_sourse.db.entity.DISHE_TABLE
import com.pas.eater.data.data_sourse.db.entity.DisheEntity

@Dao
interface DishesDao {

    @Transaction
    suspend fun updateDishes(route: List<DisheEntity>) {
        route.let { list ->
            deleteDishes()
            list.forEach { insertDishes(it) }
        }
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDishes(route: DisheEntity)

    @Query("DELETE FROM $DISHE_TABLE")
    suspend fun deleteDishes()

    @Query("SELECT * FROM $DISHE_TABLE")
    fun getRDishes(): Flow<List<DisheEntity>>
}
