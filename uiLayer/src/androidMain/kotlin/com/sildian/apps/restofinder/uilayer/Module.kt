package com.sildian.apps.restofinder.uilayer

import com.sildian.apps.restofinder.domainlayer.domainLayerModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

actual val uiLayerModule get() = module {
    includes(domainLayerModule)
    viewModel<AutomaticRestaurantSelectorViewModel> { AutomaticRestaurantSelectorViewModel(getRestaurantsUseCase = get()) }
}
