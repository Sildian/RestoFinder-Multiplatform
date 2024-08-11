package com.sildian.apps.restofinder.uilayer

import com.sildian.apps.restofinder.domainlayer.domainLayerModule
import org.koin.dsl.module

internal actual val uiLayerModule get() = module {
    includes(domainLayerModule)
    factory<AutomaticRestaurantSelectorViewModel> { AutomaticRestaurantSelectorViewModel(getRestaurantsUseCase = get()) }
}