package com.sildian.apps.restofinder.uilayer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun MainEntryPoint() {
    AutomaticRestaurantSelectorScreenEntryPoint()
}

@Composable
private fun AutomaticRestaurantSelectorScreenEntryPoint(
    viewModel: AutomaticRestaurantSelectorViewModel = viewModelInject(),
) {
    val restaurants by viewModel.restaurantsState.collectAsState()
    AutomaticRestaurantSelectorScreen(restaurants = restaurants)
}