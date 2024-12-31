package com.sildian.apps.restofinder.datalayer

import com.sildian.apps.restofinder.domainlayer.Restaurant

internal fun getHardcodedRestaurants(): List<Restaurant> =
    listOf(
        Restaurant(name = "Sette Pizza"),
        Restaurant(name = "Pide Paris"),
        Restaurant(name = "Sushirama"),
        Restaurant(name = "Café Soucoupe"),
        Restaurant(name = "Osè African Cuisine"),
        Restaurant(name = "Levain, Le Vin"),
        Restaurant(name = "Faste - Croque monsieur Paris"),
        Restaurant(name = "La Petite Louise"),
        Restaurant(name = "Les Rupins - Restaurant Parisien"),
        Restaurant(name = "Cozy poke"),
    )