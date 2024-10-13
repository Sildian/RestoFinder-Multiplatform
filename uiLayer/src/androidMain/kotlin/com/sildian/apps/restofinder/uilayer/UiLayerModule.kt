package com.sildian.apps.restofinder.uilayer

import com.sildian.apps.restofinder.core.coroutines.coroutinesModule
import com.sildian.apps.restofinder.domainlayer.domainLayerModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

actual val uiLayerModule get() = module {
    includes(domainLayerModule)
    includes(uiUtilsModule)
    includes(coroutinesModule)
    viewModel<AutomaticRestaurantSelectorViewModel> {
        AutomaticRestaurantSelectorViewModel(
            getRestaurantsUseCase = get(),
            automaticIndexSelectorFlow = get(),
            dispatcher = get(
                parameters = {
                    parametersOf(AutomaticRestaurantSelectorViewModel.AUTOMATIC_SELECTOR_THREAD_NAME)
                }
            ),
        )
    }
}
