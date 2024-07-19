package com.sildian.apps.restofinder.datalayer

import kotlin.test.Test
import kotlin.test.assertEquals

class RestaurantsRepositoryImplTest {

    @Test
    fun `GIVEN no arg WHEN getRestaurants THEN result is hardcodedRestaurants`() {
        // Given
        val repository = RestaurantsRepositoryImpl()

        // When
        val result = repository.getRestaurants()

        // Then
        val expectedResult = getHardcodedRestaurants()
        assertEquals(expected = expectedResult, actual = result)
    }
}
