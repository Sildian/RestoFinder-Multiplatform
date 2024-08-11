package com.sildian.apps.restofinder.uilayer

import androidx.lifecycle.ViewModel
import com.sildian.apps.restofinder.domainlayer.GetRestaurantsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class AutomaticRestaurantSelectorViewModel(
    private val getRestaurantsUseCase: GetRestaurantsUseCase,
) : ViewModel() {

    private val _restaurantsState = MutableStateFlow(
        getRestaurantsUseCase().map { RestaurantUi(name = it.name) }
    )
    val restaurantsState: StateFlow<List<RestaurantUi>> = _restaurantsState.asStateFlow()
}