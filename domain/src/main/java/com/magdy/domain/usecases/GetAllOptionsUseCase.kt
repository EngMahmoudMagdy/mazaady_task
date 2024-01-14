package com.magdy.domain.usecases

import com.magdy.domain.repos.CatsRepo

class GetAllOptionsUseCase(private val catsRepo: CatsRepo) {
    suspend operator fun invoke(id: Int) = catsRepo.getAllOptions(id)
}