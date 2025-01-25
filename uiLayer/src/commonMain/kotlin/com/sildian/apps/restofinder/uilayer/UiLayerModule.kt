package com.sildian.apps.restofinder.uilayer

import com.sildian.apps.restofinder.core.coroutines.coroutinesModule
import org.koin.dsl.module

val uiLayerModule = module {
    includes(viewModelModule)
    includes(uiUtilsModule)
    includes(coroutinesModule)
}
