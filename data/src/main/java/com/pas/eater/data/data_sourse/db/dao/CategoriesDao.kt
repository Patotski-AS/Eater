package com.pas.eater.data.data_sourse.db.dao

import androidx.room.*
import com.pas.eater.data.data_sourse.db.entity.CATEGORY_TABLE
import com.pas.eater.data.data_sourse.db.entity.СategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoriesDao {

    @Transaction
    suspend fun updateCategories(route: List<СategoryEntity>) {
        route.let {list->
            deleteCategories()
            list.forEach {insertCategories(it)}
        }
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategories(route: СategoryEntity)

    @Query("DELETE FROM $CATEGORY_TABLE")
    suspend fun deleteCategories()

    @Query("SELECT * FROM $CATEGORY_TABLE")
    fun getCategories(): Flow<List<СategoryEntity>>
}
