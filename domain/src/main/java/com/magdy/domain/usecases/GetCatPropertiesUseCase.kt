package com.magdy.domain.usecases

import com.magdy.domain.repos.CatsRepo

class GetCatPropertiesUseCase(private val catsRepo: CatsRepo) {
    suspend operator fun invoke(catId: Int) = catsRepo.getCatProperties(catId)
}