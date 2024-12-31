package com.sildian.apps.restofinder.datalayer

import com.sildian.apps.restofinder.domainlayer.RestaurantsRepository
import org.koin.dsl.module

val dataLayerModule = module {
    factory<RestaurantsRepository> { RestaurantsRepositoryImpl() }
}