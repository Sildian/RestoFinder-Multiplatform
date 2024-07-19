package com.sildian.apps.restofinder.datalayer

import org.koin.dsl.module

val dataLayerModule = module {
    factory<RestaurantsRepository> { RestaurantsRepositoryImpl() }
}