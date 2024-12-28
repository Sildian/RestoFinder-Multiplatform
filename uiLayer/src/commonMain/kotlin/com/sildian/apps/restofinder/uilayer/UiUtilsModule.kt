package com.sildian.apps.restofinder.uilayer

import org.koin.dsl.module

internal val uiUtilsModule = module {
    factory<AutomaticIndexSelectorFlow<RestaurantUi>> {
        RollingIndexSelectorFlow(
            indexSelectorFunction = RandomIndexSelectorFunction(maxItemsBrowseCount = 5),
            delayBetweenEachItemInMillis = 100,
        )
    }
}