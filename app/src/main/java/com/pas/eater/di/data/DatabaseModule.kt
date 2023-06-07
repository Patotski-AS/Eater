package com.pas.eater.di.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.pas.eater.data.data_sourse.db.EaterDatabase
import com.pas.eater.data.data_sourse.db.dao.CategoriesDao
import com.pas.eater.data.data_sourse.db.dao.DishesDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): EaterDatabase {
        return Room.databaseBuilder(
            appContext.applicationContext, EaterDatabase::class.java, EaterDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideDishesDao(database: EaterDatabase): DishesDao {
        return database.dishesDao
    }

    @Provides
    @Singleton
    fun provideCategoriesDao(database: EaterDatabase): CategoriesDao {
        return database.categoriesDao
    }

}


