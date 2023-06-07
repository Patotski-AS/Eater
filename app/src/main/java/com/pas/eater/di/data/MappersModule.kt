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
