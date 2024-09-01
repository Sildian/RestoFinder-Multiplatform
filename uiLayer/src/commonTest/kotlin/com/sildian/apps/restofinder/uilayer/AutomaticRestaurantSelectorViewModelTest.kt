package com.sildian.apps.restofinder.uilayer

import app.cash.turbine.test
import com.sildian.apps.restofinder.domainlayer.GetRestaurantsUseCase
import com.sildian.apps.restofinder.domainlayer.nextRestaurantDomain
import com.sildian.apps.restofinder.uilayer.AutomaticRestaurantSelectorViewModel.State as RestaurantsState
import kotlinx.coroutines.test.runTest
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals

class AutomaticRestaurantSelectorViewModelTest {

    @Test
    fun `GIVEN restaurants from useCase WHEN init THEN triggers state with Initialized RestaurantsState`() = runTest {
        // Given
        val restaurants = List(size = Random.nextInt(from = 1, until = 6)) { Random.nextRestaurantDomain() }
        val useCase = GetRestaurantsUseCase { restaurants }

        // When
        val viewModel = AutomaticRestaurantSelectorViewModel(getRestaurantsUseCase = useCase)

        // Then
        viewModel.restaurantsState.test {
            val expectedResult = RestaurantsState.Initialized(
                restaurants = restaurants.map { RestaurantUi(name = it.name) },
            )
            assertEquals(expected = expectedResult, actual = awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}