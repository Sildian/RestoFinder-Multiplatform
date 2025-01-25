package com.sildian.apps.restofinder.domainlayer

import com.sildian.apps.restofinder.core.testutils.TestOnly
import com.sildian.apps.restofinder.core.testutils.nextString
import kotlin.random.Random

@TestOnly
fun Random.nextRestaurant(
    name: String = nextString(),
): Restaurant =
    Restaurant(
        name = name,
    )