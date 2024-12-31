package com.sildian.apps.restofinder.domainlayer

import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals

class GetRestaurantsUseCaseImplTest {

    @Test
    fun `GIVEN restaurants from repository WHEN invoke THEN result is restaurants from repository`() {
        // Given
        val restaurants = List(size = Random.nextInt(from = 1, until = 6)) { Random.nextRestaurant() }
        val repository: RestaurantsRepository = object: RestaurantsRepository {
            override fun getRestaurants(): List<Restaurant> = restaurants
        }
        val useCase = GetRestaurantsUseCaseImpl(restaurantsRepository = repository)

        // When
        val result = useCase()

        // Then
        assertEquals(expected = restaurants, actual = result)
    }
}