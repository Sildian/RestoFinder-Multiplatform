package com.sildian.apps.restofinder.uilayer

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sildian.apps.restofinder.uilayer.AutomaticRestaurantSelectorViewModel.Intent as RestaurantsIntent
import com.sildian.apps.restofinder.uilayer.AutomaticRestaurantSelectorViewModel.State as RestaurantsState

@Composable
internal fun AutomaticRestaurantSelectorScreen(
    restaurantsState: RestaurantsState,
    onIntent: (RestaurantsIntent) -> Unit,
) {
    Scaffold {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Selector(restaurantsState = restaurantsState)
                Button(
                    onClick = { onIntent(RestaurantsIntent.LaunchSelection) },
                    enabled = restaurantsState !is RestaurantsState.SelectionInProgress,
                ) {
                    Text("Lancer la sélection")
                }
            }
        }
    }
}

@Composable
private fun Selector(
    restaurantsState: RestaurantsState,
    modifier: Modifier = Modifier,
) {
    AnimatedContent(
        modifier = modifier,
        targetState = restaurantsState.selectedIndex,
        transitionSpec = {
            slideInVertically { height -> height } + fadeIn() togetherWith
                    slideOutVertically { height -> -height } + fadeOut()
        }
    ) { itemIndex ->
        Text(
            text = restaurantsState.restaurants[itemIndex].name,
            style = MaterialTheme.typography.headlineSmall,
        )
    }
}