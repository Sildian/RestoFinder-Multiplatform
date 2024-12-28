package com.sildian.apps.restofinder.uilayer

import com.sildian.apps.restofinder.core.coroutines.coroutinesModule
import com.sildian.apps.restofinder.domainlayer.domainLayerModule
import org.koin.dsl.module

val uiLayerModule = module {
    includes(domainLayerModule)
    includes(viewModelModule)
    includes(uiUtilsModule)
    includes(coroutinesModule)
}
