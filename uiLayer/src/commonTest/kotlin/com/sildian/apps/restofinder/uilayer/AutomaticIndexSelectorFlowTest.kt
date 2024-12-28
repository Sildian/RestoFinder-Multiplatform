package com.sildian.apps.restofinder.uilayer

import app.cash.turbine.test
import com.sildian.apps.restofinder.core.testutils.nextString
import kotlinx.coroutines.test.runTest
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class AutomaticIndexSelectorFlowTest {

    @Test
    fun `GIVEN indexSelectorFunction throws exception WHEN invoke RollingIndexSelectorFlow THEN throws exception`() = runTest {
        // Given
        val items = List(size = 5) { Random.nextString() }
        val error = IllegalArgumentException("This is an error")
        val indexSelectorFunction = AutomaticIndexSelectorFlow.IndexSelectorFunction<String> {
            throw error
        }
        val indexSelectorFlow = RollingIndexSelectorFlow(
            indexSelectorFunction = indexSelectorFunction,
            delayBetweenEachItemInMillis = 0,
        )

        // When Then
        indexSelectorFlow(items = items).test {
            assertEquals(expected = error, actual = awaitError())
            ensureAllEventsConsumed()
        }
    }

    @Test
    fun `GIVEN indexSelectorFunction returns index below list size WHEN invoke RollingIndexSelectorFlow THEN flows indexes until selected index is reached`() = runTest {
        // Given
        val items = List(size = 5) { Random.nextString() }
        val indexSelectorFunction = AutomaticIndexSelectorFlow.IndexSelectorFunction<String> { 3 }
        val indexSelectorFlow = RollingIndexSelectorFlow(
            indexSelectorFunction = indexSelectorFunction,
            delayBetweenEachItemInMillis = 0,
        )

        // When Then
        indexSelectorFlow(items = items).test {
            assertEquals(
                expected = listOf(0, 1, 2, 3),
                actual = List(size = 4) { awaitItem() },
            )
            awaitComplete()
            ensureAllEventsConsumed()
        }
    }

    @Test
    fun `GIVEN indexSelectorFunction returns index above list size WHEN invoke RollingIndexSelectorFlow THEN flows indexes and restarts list until selected index as offset is reached`() = runTest {
        // Given
        val items = List(size = 5) { Random.nextString() }
        val indexSelectorFunction = AutomaticIndexSelectorFlow.IndexSelectorFunction<String> { 7 }
        val indexSelectorFlow = RollingIndexSelectorFlow(
            indexSelectorFunction = indexSelectorFunction,
            delayBetweenEachItemInMillis = 0,
        )

        // When Then
        indexSelectorFlow(items = items).test {
            assertEquals(
                expected = listOf(0, 1, 2, 3, 4, 0, 1, 2),
                actual = List(size = 8) { awaitItem() },
            )
            awaitComplete()
            ensureAllEventsConsumed()
        }
    }

    @Test
    fun `GIVEN a list and 0 or negative maxItemsBrowseCount WHEN invoke RandomIndexSelectorFunction THEN throws IllegalArgumentException`() {
        // Given
        val maxItemsBrowseCount = listOf(0, -1, -2).random()
        val items = List(size = Random.nextInt(from = 1, until = 6)) { Random.nextString() }
        val indexSelectorFunction = RandomIndexSelectorFunction<String>(
            maxItemsBrowseCount = maxItemsBrowseCount,
        )

        // When Then
        assertFailsWith(IllegalArgumentException::class) {
            indexSelectorFunction(items = items)
        }
    }

    @Test
    fun `GIVEN an empty list WHEN invoke RandomIndexSelectorFunction THEN throws IllegalArgumentException`() {
        // Given
        val maxItemsBrowseCount = Random.nextInt(from = 1, until = 6)
        val items = emptyList<String>()
        val indexSelectorFunction = RandomIndexSelectorFunction<String>(
            maxItemsBrowseCount = maxItemsBrowseCount,
        )

        // When Then
        assertFailsWith(IllegalArgumentException::class) {
            indexSelectorFunction(items = items)
        }
    }

    @Test
    fun `GIVEN a list and positive maxItemsBrowseCount WHEN invoke RandomIndexSelectorFunction THEN result is a random int between 0 and list size multiplied by maxItemsBrowseCount`() {
        // Given
        val maxItemsBrowseCount = Random.nextInt(from = 1, until = 6)
        val items = List(size = Random.nextInt(from = 1, until = 6)) { Random.nextString() }
        val indexSelectorFunction = RandomIndexSelectorFunction<String>(
            maxItemsBrowseCount = maxItemsBrowseCount,
        )

        // When
        val result = indexSelectorFunction(items = items)

        // Then
        val expectedRange = IntRange(start = 0, endInclusive = items.size * maxItemsBrowseCount - 1)
        assertTrue { result in expectedRange }
    }
}