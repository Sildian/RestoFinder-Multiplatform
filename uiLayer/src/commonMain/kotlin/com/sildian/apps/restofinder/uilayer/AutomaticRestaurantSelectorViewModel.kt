package com.sildian.apps.restofinder.uilayer

import androidx.lifecycle.ViewModel
import com.sildian.apps.restofinder.domainlayer.GetRestaurantsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class AutomaticRestaurantSelectorViewModel(
    private val getRestaurantsUseCase: GetRestaurantsUseCase,
) : ViewModel() {

    private val _restaurantsState: MutableStateFlow<State> = MutableStateFlow(
        State.Initialized(
            restaurants = getRestaurantsUseCase().map { RestaurantUi(name = it.name) },
        )
    )
    val restaurantsState: StateFlow<State> = _restaurantsState.asStateFlow()

    sealed interface State {
        val restaurants: List<RestaurantUi>
        data class Initialized(
            override val restaurants: List<RestaurantUi>,
        ) : State
        data class SelectionInProgress(
            override val restaurants: List<RestaurantUi>,
            val currentIndex: Int,
        ) : State
        data class SelectionDone(
            override val restaurants: List<RestaurantUi>,
            val selectedIndex: Int,
        ) : State
    }
}