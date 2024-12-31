package com.sildian.apps.restofinder.domainlayer

import org.koin.dsl.module

val domainLayerModule = module {
    factory<GetRestaurantsUseCase> { GetRestaurantsUseCaseImpl(restaurantsRepository = get()) }
}