package com.magdy.domain.usecases

import com.magdy.domain.repos.CatsRepo

class GetAllCatsUseCase(private val catsRepo: CatsRepo) {
    suspend operator fun invoke() = catsRepo.getAllCats()
}