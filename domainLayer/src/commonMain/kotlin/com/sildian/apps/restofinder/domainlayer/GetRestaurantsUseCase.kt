package com.sildian.apps.restofinder.domainlayer

fun interface GetRestaurantsUseCase {
    operator fun invoke(): List<Restaurant>
}

internal class GetRestaurantsUseCaseImpl(
    private val restaurantsRepository: RestaurantsRepository,
): GetRestaurantsUseCase {

    override operator fun invoke(): List<Restaurant> =
        restaurantsRepository.getRestaurants()
}