package com.sildian.apps.restofinder.uilayer

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.sildian.apps.restofinder.core.testutils.CoroutineTestRule
import com.sildian.apps.restofinder.domainlayer.GetRestaurantsUseCase
import com.sildian.apps.restofinder.domainlayer.nextRestaurantDomain
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import com.sildian.apps.restofinder.uilayer.AutomaticRestaurantSelectorViewModel.Intent as RestaurantsIntent
import com.sildian.apps.restofinder.uilayer.AutomaticRestaurantSelectorViewModel.State as RestaurantsState
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals

class AutomaticRestaurantSelectorViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private fun initViewModel(
        getRestaurantsUseCase: GetRestaurantsUseCase = GetRestaurantsUseCase {
            listOf(Random.nextRestaurantDomain())
        },
        automaticIndexSelectorFlow: AutomaticIndexSelectorFlow<RestaurantUi> = AutomaticIndexSelectorFlow {
            flowOf(0)
        },
    ): AutomaticRestaurantSelectorViewModel =
        AutomaticRestaurantSelectorViewModel(
            getRestaurantsUseCase = getRestaurantsUseCase,
            automaticIndexSelectorFlow = automaticIndexSelectorFlow,
            dispatcher = coroutineTestRule.dispatcher,
        )

    @Test
    fun `GIVEN restaurants from useCase WHEN init THEN triggers Initialized state`() = runTest {
        // Given
        val restaurants = List(size = Random.nextInt(from = 1, until = 6)) { Random.nextRestaurantDomain() }
        val useCase = GetRestaurantsUseCase { restaurants }

        // When
        val viewModel = initViewModel(getRestaurantsUseCase = useCase)

        // Then
        viewModel.restaurantsState.test {
            val expectedResult = RestaurantsState.Initialized(
                restaurants = restaurants.map { RestaurantUi(name = it.name) },
            )
            assertEquals(expected = expectedResult, actual = awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `fun GIVEN exception from indexSelectorFlow WHEN LaunchSelection intent THEN triggers SelectionError state`() = runTest {
        // Given
        val restaurants = List(size = 3) { Random.nextRestaurantDomain() }
        val useCase = GetRestaurantsUseCase { restaurants }
        val error = IllegalArgumentException()
        val indexSelectorFlow = AutomaticIndexSelectorFlow<RestaurantUi> { flow { throw error } }
        val viewModel = initViewModel(
            getRestaurantsUseCase = useCase,
            automaticIndexSelectorFlow = indexSelectorFlow,
        )

        // When
        viewModel.onIntent(intent = RestaurantsIntent.LaunchSelection)

        // Then
        viewModel.restaurantsState.test {
            val expectedResult = RestaurantsState.SelectionError(
                restaurants = restaurants.map { RestaurantUi(name = it.name) },
                selectedIndex = 0,
                error = error,
            )
            assertEquals(expectedResult, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `fun GIVEN indexes from indexSelectorFlow WHEN LaunchSelection intent THEN triggers SelectionDone state after completion`() = runTest {
        // Given
        val restaurants = List(size = 3) { Random.nextRestaurantDomain() }
        val useCase = GetRestaurantsUseCase { restaurants }
        val indexes = listOf(0, 1, 2)
        val indexSelectorFlow = AutomaticIndexSelectorFlow<RestaurantUi> {
            flow { indexes.forEach { emit(it) } }
        }
        val viewModel = initViewModel(
            getRestaurantsUseCase = useCase,
            automaticIndexSelectorFlow = indexSelectorFlow,
        )

        // When
        viewModel.onIntent(intent = RestaurantsIntent.LaunchSelection)

        // Then
        viewModel.restaurantsState.test {
            val expectedResult = AutomaticRestaurantSelectorViewModel.State.SelectionDone(
                restaurants = restaurants.map { RestaurantUi(name = it.name) },
                selectedIndex = indexes.last(),
            )
            assertEquals(expected = expectedResult, actual = awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}