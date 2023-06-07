package com.pas.eater.di.data

import com.pas.eater.data.data_sourse.api.ApiService
import com.pas.eater.data.data_sourse.db.dao.CategoriesDao
import com.pas.eater.data.data_sourse.db.dao.DishesDao
import com.pas.eater.data.mappers.ErrorMapper
import com.pas.eater.data.mappers.MapCategory
import com.pas.eater.data.mappers.MapDishes
import com.pas.eater.data.repository.CategoriesRepositoryImpl
import com.pas.eater.data.repository.DishesRepositoryImpl
import com.pas.eater.domain.repository.CategoriesRepository
import com.pas.eater.domain.repository.DishesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCategoriesRepository(
        api: ApiService,
        db: CategoriesDao,
        mapCategory: MapCategory,
        errorMapper: ErrorMapper
    ): CategoriesRepository {
        return CategoriesRepositoryImpl(
            api = api,
            db = db,
            mapCategory = mapCategory,
            errorMapper = errorMapper
        )
    }

    @Provides
    @Singleton
    fun provideDishesRepository(
        api: ApiService,
        db: DishesDao,
        mapDishes: MapDishes,
        errorMapper: ErrorMapper
    ): DishesRepository {
        return DishesRepositoryImpl(
            api = api,
            db = db,
            mapDishes = mapDishes,
            errorMapper = errorMapper
        )
    }
}
