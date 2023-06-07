package com.pas.eater.data.repository

import com.pas.eater.data.data_sourse.api.ApiService
import com.pas.eater.data.data_sourse.api.helper.RemoteException
import com.pas.eater.data.data_sourse.db.dao.CategoriesDao
import com.pas.eater.data.mappers.ErrorMapper
import com.pas.eater.data.mappers.MapCategory
import com.pas.eater.domain.models.Category
import com.pas.eater.domain.repository.CategoriesRepository
import com.pas.eater.domain.util.Record
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CategoriesRepositoryImpl(
    private val api: ApiService,
    private val db: CategoriesDao,
    private val mapCategory: MapCategory,
    private val errorMapper: ErrorMapper
) : CategoriesRepository {

    override suspend fun getCategoriesFromApi(): Record<List<Category>> {
        return try {
            api.getCategory().сategories.run {
                Record(mapCategory.categoryPojoToCategory.mapList(this), null)
            }
        } catch (e: RemoteException) {
            errorMapper.mapErrorRecord(e)
        }
    }

    override suspend fun insertCategories(categories: List<Category>) {
        db.updateCategories(mapCategory.categoryToCategoryEntity.mapList(categories))
    }

    override fun getCategoriesFromDB(): Flow<List<Category>> {
        return db.getCategories().map { mapCategory.categoryEntityToCategory.mapList(it) }
    }
}