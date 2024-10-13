package com.sildian.apps.restofinder.core.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.IO
import kotlinx.coroutines.newSingleThreadContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val DISPATCHER_MAIN_NAME = "coroutineMain"
const val DISPATCHER_IO_NAME = "coroutineIO"

@OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
val coroutinesModule = module {
    single<CoroutineDispatcher>(named(DISPATCHER_MAIN_NAME)) { Dispatchers.Main }
    single<CoroutineDispatcher>(named(DISPATCHER_IO_NAME)) { Dispatchers.IO }
    factory<CoroutineDispatcher> { params -> newSingleThreadContext(name = params.get()) }
}