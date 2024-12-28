package com.sildian.apps.restofinder.uilayer

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

internal actual val viewModelModule get() = module {
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