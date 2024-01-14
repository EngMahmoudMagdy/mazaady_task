package com.magdy.data.repos

import com.magdy.core.models.CategoriesResponse
import com.magdy.core.models.CategoryPropertiesResponse
import com.magdy.core.services.BaseApiResponse
import com.magdy.core.services.NetworkResult
import com.magdy.data.datasources.CategoryRemoteDatasource
import com.magdy.domain.repos.CatsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CatsRepoImpl @Inject constructor(
    val categoryRemoteDatasource: CategoryRemoteDatasource,
) : CatsRepo, BaseApiResponse() {
    override suspend fun getAllCats(): Flow<NetworkResult<CategoriesResponse>> {
        return flow {
            emit(
                safeApiCall {
                    categoryRemoteDatasource.getAllCats()
                }
            )
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getCatProperties(catId: Int): Flow<NetworkResult<CategoryPropertiesResponse>> {
        return flow {
            emit(
                safeApiCall {
                    categoryRemoteDatasource.getCatProperties(catId)
                }
            )
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getAllOptions(id: Int): Flow<NetworkResult<CategoryPropertiesResponse>> {
        return flow {
            emit(
                safeApiCall {
                    categoryRemoteDatasource.getAllOptions(id)
                }
            )
        }.flowOn(Dispatchers.IO)
    }
}