package com.magdy.domain.repos

import com.magdy.core.models.CategoriesResponse
import com.magdy.core.models.CategoryPropertiesResponse
import com.magdy.core.services.NetworkResult
import kotlinx.coroutines.flow.Flow

interface CatsRepo {
    suspend fun getAllCats() : Flow<NetworkResult<CategoriesResponse>>
    suspend fun getCatProperties(catId:Int) : Flow<NetworkResult<CategoryPropertiesResponse>>
    suspend fun getAllOptions(id:Int) : Flow<NetworkResult<CategoryPropertiesResponse>>
}