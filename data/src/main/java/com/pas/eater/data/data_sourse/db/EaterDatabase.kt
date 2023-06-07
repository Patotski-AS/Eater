package com.pas.eater.data.data_sourse.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pas.eater.data.data_sourse.db.dao.CategoriesDao
import com.pas.eater.data.data_sourse.db.dao.DishesDao
import com.pas.eater.data.data_sourse.db.entity.DisheEntity
import com.pas.eater.data.data_sourse.db.entity.СategoryEntity
import com.pas.eater.data.data_sourse.db.util.TypeConverter

@Database(
    entities = [
        СategoryEntity::class,
        DisheEntity::class,
    ], version = 1, exportSchema = false
)
@TypeConverters(TypeConverter::class)
abstract class EaterDatabase: RoomDatabase() {
    abstract val dishesDao: DishesDao
    abstract val categoriesDao: CategoriesDao

    companion object {
        const val DATABASE_NAME = "eater_db"
    }

}
