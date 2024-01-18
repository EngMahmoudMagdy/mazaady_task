package com.magdy.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.magdy.core.models.*
import com.magdy.core.services.NetworkResult
import com.magdy.domain.repos.CatsRepo
import com.magdy.domain.usecases.GetAllCatsUseCase
import com.magdy.domain.usecases.GetAllOptionsUseCase
import com.magdy.domain.usecases.GetCatPropertiesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetAllOptionsUseCaseTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()


    @Mock
    private lateinit var catsRepo: CatsRepo

    lateinit var getAllOptionsUseCase: GetAllOptionsUseCase

    val tCatId = 1

    val catPropertiesResponse =
        CategoryPropertiesResponse(
            data = arrayListOf(CategoryProperty(id = 123))
        )

    @Before
    fun setUp() {
        getAllOptionsUseCase = GetAllOptionsUseCase(catsRepo)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testGetNowPlayingMoviesList() = testCoroutineRule.runTest {
        whenever(catsRepo.getAllOptions(tCatId)).thenAnswer {
            flowOf(
                NetworkResult.Success(
                    catPropertiesResponse
                )
            )
        }
        val call = getAllOptionsUseCase(tCatId)
        val flow = call.first()

        Assert.assertEquals(flow.data?.data?.get(0)?.id, catPropertiesResponse.data?.get(0)?.id)
    }
}