package com.pas.eater.di.domain

import com.pas.eater.domain.repository.BasketRepository
import com.pas.eater.domain.repository.CategoriesRepository
import com.pas.eater.domain.repository.DishesRepository
import com.pas.eater.domain.use_case.DeleteBasketItemUseCase
import com.pas.eater.domain.use_case.DeleteBasketUseCase
import com.pas.eater.domain.use_case.GetBasketUseCase
import com.pas.eater.domain.use_case.GetCategoriesUseCase
import com.pas.eater.domain.use_case.GetDishesUseCase
import com.pas.eater.domain.use_case.InsertBasketItemUseCase
import com.pas.eater.domain.use_case.UpdateCategoriesUseCase
import com.pas.eater.domain.use_case.UpdateDishesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideUpdateCategoriesUseCase(
        repo: CategoriesRepository,
    ): UpdateCategoriesUseCase {
        return UpdateCategoriesUseCase(repo)
    }

    @Singleton
    @Provides
    fun provideGetCategoriesUseCase(
        repo: CategoriesRepository,
    ): GetCategoriesUseCase {
        return GetCategoriesUseCase(repo)
    }

    @Singleton
    @Provides
    fun provideUpdateDishesUseCase(
        repo: DishesRepository,
    ): UpdateDishesUseCase {
        return UpdateDishesUseCase(repo)
    }

    @Singleton
    @Provides
    fun provideGetDishesUseCase(
        repo: DishesRepository,
    ): GetDishesUseCase {
        return GetDishesUseCase(repo)
    }

    @Singleton
    @Provides
    fun provideGetBasketUseCase(
        repo: BasketRepository,
    ): GetBasketUseCase {
        return GetBasketUseCase(repo)
    }

    @Singleton
    @Provides
    fun provideInsertBasketItemUseCase(
        repo: BasketRepository,
    ): InsertBasketItemUseCase {
        return InsertBasketItemUseCase(repo)
    }

    @Singleton
    @Provides
    fun provideDeleteBasketItemUseCase(
        repo: BasketRepository,
    ): DeleteBasketItemUseCase {
        return DeleteBasketItemUseCase(repo)
    }

    @Singleton
    @Provides
    fun provideDeleteBasketUseCase(
        repo: BasketRepository,
    ): DeleteBasketUseCase {
        return DeleteBasketUseCase(repo)
    }
}
