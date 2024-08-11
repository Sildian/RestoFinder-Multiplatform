package com.sildian.apps.restofinder.uilayer

import app.cash.turbine.test
import com.sildian.apps.restofinder.domainlayer.GetRestaurantsUseCase
import com.sildian.apps.restofinder.domainlayer.nextRestaurantDomain
import kotlinx.coroutines.runBlocking
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals

class AutomaticRestaurantSelectorViewModelTest {

    @Test
    fun `GIVEN restaurants from useCase WHEN init THEN triggers state with restaurants`() = runBlocking {
        // Given
        val restaurants = List(size = Random.nextInt(from = 1, until = 6)) { Random.nextRestaurantDomain() }
        val useCase = GetRestaurantsUseCase { restaurants }

        // When
        val viewModel = AutomaticRestaurantSelectorViewModel(getRestaurantsUseCase = useCase)

        // Then
        viewModel.restaurantsState.test {
            val expectedResult = restaurants.map { RestaurantUi(name = it.name) }
            assertEquals(expected = expectedResult, actual = awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}