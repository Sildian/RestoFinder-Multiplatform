package com.sildian.apps.restofinder.uilayer

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import org.koin.compose.koinInject

@Composable
internal actual inline fun <reified T: ViewModel> viewModelInject(): T = koinInject<T>()