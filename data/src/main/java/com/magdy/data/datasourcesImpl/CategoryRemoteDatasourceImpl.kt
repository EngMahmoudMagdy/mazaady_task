package com.magdy.data.datasourcesImpl

import com.magdy.core.models.CategoriesResponse
import com.magdy.core.models.CategoryPropertiesResponse
import com.magdy.core.services.ApiService
import com.magdy.data.datasources.CategoryRemoteDatasource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Response
import javax.inject.Inject

@ActivityRetainedScoped
class CategoryRemoteDatasourceImpl @Inject constructor(val apiService: ApiService) : CategoryRemoteDatasource {
    override suspend fun getAllCats(): Response<CategoriesResponse> = apiService.getAllCats()

    override suspend fun getCatProperties(catId: Int): Response<CategoryPropertiesResponse> = apiService.getCatProperties(catId)

    override suspend fun getAllOptions(
        id: Int,
    ): Response<CategoryPropertiesResponse> = apiService.getAllOptions(id)
}