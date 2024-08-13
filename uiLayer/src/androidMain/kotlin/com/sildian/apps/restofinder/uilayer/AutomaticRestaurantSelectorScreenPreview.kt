package com.sildian.apps.restofinder.uilayer

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun AutomaticRestaurantSelectorScreenPreview() {
    AutomaticRestaurantSelectorScreen(
        restaurants = listOf(
            RestaurantUi(name = "Sette Pizza"),
            RestaurantUi(name = "Pide Paris"),
            RestaurantUi(name = "Shushirama"),
            RestaurantUi(name = "Café Soucoupe"),
            RestaurantUi(name = "Osè African Cuisine"),
        )
    )
}