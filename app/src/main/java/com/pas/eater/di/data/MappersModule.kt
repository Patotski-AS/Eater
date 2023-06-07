package com.pas.eater.di.data

import com.pas.eater.data.mappers.ErrorMapper
import com.pas.eater.data.mappers.MapCategory
import com.pas.eater.data.mappers.MapCategoryEntityToCategory
import com.pas.eater.data.mappers.MapCategoryPojoToCategory
import com.pas.eater.data.mappers.MapCategoryToCategoryEntity
import com.pas.eater.data.mappers.MapDishesPojoToDishesEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MappersModule {

    @Provides
    fun provideErrorMapper(): ErrorMapper {
        return ErrorMapper()
    }

    @Provides
    fun provideMapDishesPojoToDishesEntity(): MapDishesPojoToDishesEntity {
        return MapDishesPojoToDishesEntity()
    }


    @Provides
    @Singleton
    fun provideMapCategory(
        categoryPojoToCategory: MapCategoryPojoToCategory,
        categoryToCategoryEntity: MapCategoryToCategoryEntity,
        categoryEntityToCategory: MapCategoryEntityToCategory
    ): MapCategory {
        return MapCategory(
            categoryPojoToCategory = categoryPojoToCategory,
            categoryToCategoryEntity = categoryToCategoryEntity,
            categoryEntityToCategory = categoryEntityToCategory
        )
    }


    @Provides
    fun provideMapCategoryPojoToCategory(): MapCategoryPojoToCategory {
        return MapCategoryPojoToCategory()
    }

    @Provides
    fun provideMapCategoryPojoToCategoryEntity(): MapCategoryToCategoryEntity {
        return MapCategoryToCategoryEntity()
    }

    @Provides
    fun provideMapCategoryEntityToCategory(): MapCategoryEntityToCategory {
        return MapCategoryEntityToCategory()
    }

}
