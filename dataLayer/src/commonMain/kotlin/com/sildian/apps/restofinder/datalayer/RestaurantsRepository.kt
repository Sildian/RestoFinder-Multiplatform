package com.sildian.apps.restofinder.datalayer

import com.sildian.apps.restofinder.domainlayer.Restaurant
import com.sildian.apps.restofinder.domainlayer.RestaurantsRepository

internal class RestaurantsRepositoryImpl : RestaurantsRepository {

    override fun getRestaurants(): List<Restaurant> =
        getHardcodedRestaurants()
}