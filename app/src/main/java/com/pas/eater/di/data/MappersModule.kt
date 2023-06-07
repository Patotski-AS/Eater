package com.pas.eater.di.data

import com.pas.eater.data.mappers.MapDishesPojoToDishesEntity
import com.pas.eater.data.mappers.MapCategoryPojoToCategoryEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MappersModule {

    @Provides
    fun provideMapDishesPojoToDishesEntity(): MapDishesPojoToDishesEntity {
        return MapDishesPojoToDishesEntity()
    }

    @Provides
    fun provideMapCategoryPojoToCategoryEntity(): MapCategoryPojoToCategoryEntity {
        return MapCategoryPojoToCategoryEntity()
    }
}
