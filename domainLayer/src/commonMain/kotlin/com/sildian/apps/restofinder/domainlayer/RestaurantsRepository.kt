package com.sildian.apps.restofinder.domainlayer

interface RestaurantsRepository {
    fun getRestaurants(): List<Restaurant>
}