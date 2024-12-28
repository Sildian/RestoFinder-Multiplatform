package com.sildian.apps.restofinder.uilayer

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
internal actual inline fun <reified T: ViewModel> viewModelInject(): T = koinViewModel<T>()