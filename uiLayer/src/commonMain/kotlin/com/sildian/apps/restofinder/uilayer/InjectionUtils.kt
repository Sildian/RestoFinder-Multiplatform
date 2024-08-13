package com.sildian.apps.restofinder.uilayer

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel

@Composable
internal expect inline fun <reified T: ViewModel> viewModelInject(): T