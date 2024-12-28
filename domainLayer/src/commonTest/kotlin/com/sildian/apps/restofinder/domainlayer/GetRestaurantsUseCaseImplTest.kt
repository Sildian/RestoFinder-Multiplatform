package com.sildian.apps.restofinder.domainlayer

import com.sildian.apps.restofinder.datalayer.Restaurant
import com.sildian.apps.restofinder.datalayer.RestaurantsRepository
import com.sildian.apps.restofinder.datalayer.nextRestaurant
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
        val expectedResult = restaurants.map { RestaurantDomain(name = it.name) }
        assertEquals(expected = expectedResult, actual = result)
    }
}