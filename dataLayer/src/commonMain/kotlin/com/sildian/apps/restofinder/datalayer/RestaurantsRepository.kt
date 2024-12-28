package com.sildian.apps.restofinder.datalayer

interface RestaurantsRepository {

    fun getRestaurants(): List<Restaurant>
}

internal class RestaurantsRepositoryImpl : RestaurantsRepository {

    override fun getRestaurants(): List<Restaurant> =
        getHardcodedRestaurants()
}