package com.sildian.apps.restofinder.domainlayer

import com.sildian.apps.restofinder.datalayer.RestaurantsRepository

fun interface GetRestaurantsUseCase {
    operator fun invoke(): List<RestaurantDomain>
}

internal class GetRestaurantsUseCaseImpl(
    private val restaurantsRepository: RestaurantsRepository,
): GetRestaurantsUseCase {

    override operator fun invoke(): List<RestaurantDomain> =
        restaurantsRepository.getRestaurants().map { RestaurantDomain(name = it.name) }
}