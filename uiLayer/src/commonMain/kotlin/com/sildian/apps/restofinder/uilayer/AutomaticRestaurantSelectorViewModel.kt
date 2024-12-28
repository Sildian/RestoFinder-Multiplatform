package com.sildian.apps.restofinder.uilayer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sildian.apps.restofinder.domainlayer.GetRestaurantsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

internal class AutomaticRestaurantSelectorViewModel(
    private val getRestaurantsUseCase: GetRestaurantsUseCase,
    private val automaticIndexSelectorFlow: AutomaticIndexSelectorFlow<RestaurantUi>,
    private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _restaurantsState: MutableStateFlow<State> = MutableStateFlow(
        State.Initialized(
            restaurants = getRestaurantsUseCase().map { RestaurantUi(name = it.name) },
        )
    )
    val restaurantsState: StateFlow<State> = _restaurantsState.asStateFlow()

    fun onIntent(intent: Intent) {
        when (intent) {
            is Intent.LaunchSelection -> launchSelection()
        }
    }

    private fun launchSelection() {
        viewModelScope.launch(dispatcher) {
            automaticIndexSelectorFlow(items = restaurantsState.value.restaurants)
                .onEach { index ->
                    _restaurantsState.value = State.SelectionInProgress(
                        restaurants = restaurantsState.value.restaurants,
                        selectedIndex = index,
                    )
                }.onCompletion { error ->
                    if (error == null) {
                        _restaurantsState.value = State.SelectionDone(
                            restaurants = restaurantsState.value.restaurants,
                            selectedIndex = restaurantsState.value.selectedIndex,
                        )
                    }
                }.catch { error ->
                    _restaurantsState.value = State.SelectionError(
                        restaurants = restaurantsState.value.restaurants,
                        selectedIndex = restaurantsState.value.selectedIndex,
                        error = error,
                    )
                }.collect()
        }
    }

    sealed interface State {
        val restaurants: List<RestaurantUi>
        val selectedIndex: Int
        data class Initialized(
            override val restaurants: List<RestaurantUi>,
        ) : State {
            override val selectedIndex: Int = 0
        }
        data class SelectionInProgress(
            override val restaurants: List<RestaurantUi>,
            override val selectedIndex: Int,
        ) : State
        data class SelectionError(
            override val restaurants: List<RestaurantUi>,
            override val selectedIndex: Int,
            val error: Throwable,
        ) : State
        data class SelectionDone(
            override val restaurants: List<RestaurantUi>,
            override val selectedIndex: Int,
        ) : State
    }

    sealed interface Intent {
        data object LaunchSelection : Intent
    }

    companion object {
        const val AUTOMATIC_SELECTOR_THREAD_NAME = "automaticSelectorThread"
    }
}