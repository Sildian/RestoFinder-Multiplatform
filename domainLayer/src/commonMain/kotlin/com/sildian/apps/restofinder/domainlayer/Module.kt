package com.sildian.apps.restofinder.domainlayer

import com.sildian.apps.restofinder.datalayer.dataLayerModule
import org.koin.dsl.module

val domainLayerModule = module {
    includes(dataLayerModule)
    factory<GetRestaurantsUseCase> { GetRestaurantsUseCaseImpl(restaurantsRepository = get()) }
}