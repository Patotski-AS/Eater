package com.pas.eater.data.data_sourse.db.dao

import androidx.room.*
import com.pas.eater.data.data_sourse.db.entity.BASKET_TABLE
import com.pas.eater.data.data_sourse.db.entity.BasketItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BasketDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBasketItem(item: BasketItemEntity)

    @Query("DELETE from $BASKET_TABLE WHERE id = :id")
    suspend fun deleteItem(id: Int)

    @Query("DELETE FROM $BASKET_TABLE")
    suspend fun deleteBasket()


    @Query("SELECT * FROM $BASKET_TABLE")
    fun getBasket(): Flow<List<BasketItemEntity>>
}
