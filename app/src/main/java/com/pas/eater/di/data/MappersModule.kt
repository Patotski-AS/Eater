package com.pas.eater.di.data

import com.pas.eater.data.mappers.ErrorMapper
import com.pas.eater.data.mappers.MapBasket
import com.pas.eater.data.mappers.MapBasketItemEntityToBasketItem
import com.pas.eater.data.mappers.MapBasketItemToBasketItemEntity
import com.pas.eater.data.mappers.MapCategory
import com.pas.eater.data.mappers.MapCategoryEntityToCategory
import com.pas.eater.data.mappers.MapCategoryPojoToCategory
import com.pas.eater.data.mappers.MapCategoryToCategoryEntity
import com.pas.eater.data.mappers.MapDishToDisheEntity
import com.pas.eater.data.mappers.MapDisheEntityToDish
import com.pas.eater.data.mappers.MapDishes
import com.pas.eater.data.mappers.MapDishesPojoToDish
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
    @Singleton
    fun provideMapCategory(
        categoryPojoToCategory: MapCategoryPojoToCategory,
        categoryToCategoryEntity: MapCategoryToCategoryEntity,
        categoryEntityToCategory: MapCategoryEntityToCategory,
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

    @Provides
    @Singleton
    fun provideMapDishes(
        dishesPojoToDish: MapDishesPojoToDish,
        dishToDisheEntity: MapDishToDisheEntity,
        disheEntityToDish: MapDisheEntityToDish,
    ): MapDishes {
        return MapDishes(
            dishesPojoToDish = dishesPojoToDish,
            dishToDisheEntity = dishToDisheEntity,
            disheEntityToDish = disheEntityToDish
        )
    }

    @Provides
    fun provideMapDishesPojoToDish(): MapDishesPojoToDish {
        return MapDishesPojoToDish()
    }

    @Provides
    fun provideMapDishToDisheEntity(): MapDishToDisheEntity {
        return MapDishToDisheEntity()
    }

    @Provides
    fun provideMapDisheEntityToDish(): MapDisheEntityToDish {
        return MapDisheEntityToDish()
    }


    @Provides
    @Singleton
    fun provideMapBasket(
        itemToEntity: MapBasketItemToBasketItemEntity,
        entityToItem: MapBasketItemEntityToBasketItem,
    ): MapBasket {
        return MapBasket(
            itemToEntity = itemToEntity, entityToItem = entityToItem
        )
    }

    @Provides
    fun provideMapBasketItemToBasketItemEntity(): MapBasketItemToBasketItemEntity {
        return MapBasketItemToBasketItemEntity()
    }

    @Provides
    fun provideMapBasketItemEntityToBasketItem(): MapBasketItemEntityToBasketItem {
        return MapBasketItemEntityToBasketItem()
    }

}
