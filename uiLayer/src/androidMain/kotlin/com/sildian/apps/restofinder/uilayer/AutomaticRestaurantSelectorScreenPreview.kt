package com.sildian.apps.restofinder.uilayer

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sildian.apps.restofinder.uilayer.AutomaticRestaurantSelectorViewModel.State as RestaurantsState

@Preview
@Composable
private fun AutomaticRestaurantSelectorScreenPreview() {
    AutomaticRestaurantSelectorScreen(
        restaurantsState = RestaurantsState.Initialized(
            restaurants = listOf(
                RestaurantUi(name = "Sette Pizza"),
                RestaurantUi(name = "Pide Paris"),
                RestaurantUi(name = "Shushirama"),
                RestaurantUi(name = "Café Soucoupe"),
                RestaurantUi(name = "Osè African Cuisine"),
            )
        )
    )
}